package fr.cyu.depinfo.activity.server;

import com.google.common.hash.Hashing;
import com.google.common.primitives.Ints;
import fr.cyu.depinfo.activity.dao.*;
import fr.cyu.depinfo.activity.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class NetworkProcess implements Runnable {
    private static final Logger logger = LogManager.getLogger();

    private Socket socket;
    private SessionFactory sessionFactory;

    public NetworkProcess(SessionFactory sessionFactory, Socket socket) {
        this(socket, sessionFactory);
    }

    public NetworkProcess(Socket socket, SessionFactory sessionFactory) {
        this.socket = socket;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        logger.debug("New client connected from {}:{}", socket.getInetAddress().getHostAddress(), socket.getPort());

        RoomDao roomDao = new RoomDao(sessionFactory);
        BuildingDao buildingDao = new BuildingDao(sessionFactory);
        PersonDao personDao = new PersonDao(sessionFactory);
        StaffDao staffDao = new StaffDao(sessionFactory);
        BuildingLogDao buildingLogDao = new BuildingLogDao(sessionFactory);
        RoomLogDao roomLogDao = new RoomLogDao(sessionFactory);

        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Integer code = is.read();
            ProtocolCode protoCode;
            if (code != -1) {
                protoCode = ProtocolCode.fromCode(code);
                if (protoCode != ProtocolCode.ROOMID && protoCode != ProtocolCode.BUILDINGID) {
                    os.write(ProtocolCode.BADFORMAT.getCode());
                    throw new ProtocolException("Bad format");
                }
            } else {
                throw new ProtocolException("Bad protocol!");
            }

            byte[] data = new byte[4];
            Room room;
            Building building;
            if (is.read(data, 0, 4) == 4) {
                Integer roomBuildingId = Ints.fromByteArray(data);
                if (protoCode == ProtocolCode.ROOMID) {
                    room = roomDao.read(roomBuildingId);
                    if (room == null) {
                        os.write(ProtocolCode.BADDATA.getCode());
                        throw new BadDataException("The room does not exist.");
                    }
                } else {
                    building = buildingDao.read(roomBuildingId);
                    if (building == null) {
                        os.write(ProtocolCode.BADDATA.getCode());
                        throw new BadDataException("The building does not exist.");
                    }
                }
            } else {
                throw new ProtocolException("Bad protocol!");
            }

            os.write(ProtocolCode.DATAOK.getCode());

            code = is.read();
            if (code != -1) {
                protoCode = ProtocolCode.fromCode(code);
                if (protoCode != ProtocolCode.USERID) {
                    os.write(ProtocolCode.BADFORMAT.getCode());
                    throw new ProtocolException("Bad format");
                }
            } else {
                throw new ProtocolException("Bad protocol!");
            }

            Person person;
            Staff staff;
            if (is.read(data, 0, 4) == 4) {
                Integer personId = Ints.fromByteArray(data);
                person = personDao.read(personId);
                staff = staffDao.read(personId);
                if (person == null) {
                    os.write(ProtocolCode.BADDATA.getCode());
                    throw new BadDataException("The person does not exist.");
                }
            } else {
                throw new ProtocolException("Bad protocol!");
            }

            byte[] toSend = new byte[8];
            if (staff != null) {
                toSend[0] = ProtocolCode.ACCESSOK.getCode().byteValue();
            } else {
                logger.info("TODO");
            }

            toSend[1] = ProtocolCode.NONCE.getCode().byteValue();
            String nonce = OneTimePassword.totp(person.getAccessPin().getBytes(), 1);
            byte[] nonceArray = nonce.getBytes();
            logger.debug("Nonce size = {}", nonceArray.length);
            System.arraycopy(nonceArray, 0, toSend, 1, 6);

            os.write(toSend, 0, 8);

            code = is.read();
            if (code != -1) {
                protoCode = ProtocolCode.fromCode(code);
                if (protoCode != ProtocolCode.PASSWD) {
                    os.write(ProtocolCode.BADFORMAT.getCode());
                    throw new ProtocolException("Bad format");
                }
            } else {
                throw new ProtocolException("Bad protocol!");
            }

            data = new byte[32];
            if (is.read(data, 0, 32) != 32) {
                throw new ProtocolException("Bad protocol!");
            }

            byte[] serverHash = Hashing.sha256().hashString(person.getAccessPin().concat(nonce), StandardCharsets.UTF_8).asBytes();
            if (data != serverHash) {
                os.write(ProtocolCode.BADDATA.getCode());
                throw new BadDataException("Incorrect password.");
            }

            os.write(ProtocolCode.DATAOK.getCode());
        } catch (IOException | BadDataException e) {
            logger.error(e);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            logger.error("An error occurred when trying to generate the nonce.", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error("Socket is currently used by another Thread and cannot be closed.", e);
            }
        }
    }
}
