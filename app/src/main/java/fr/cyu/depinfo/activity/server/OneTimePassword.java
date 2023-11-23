package fr.cyu.depinfo.activity.server;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class OneTimePassword {
    public static final String ALGORITHM = "HmacSHA512";

    public static String hotp(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        mac.init(secretKeySpec);
        Integer otp = Ints.fromByteArray(mac.doFinal(data)) % Double.valueOf(Math.pow(10, 6)).intValue();
        otp = Math.abs(otp);
        return Strings.padStart(String.valueOf(otp), 6, '0');
    }

    public static String totp(byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
        return totp(data, 30);
    }

    public static String totp(byte[] data, int inverval) throws NoSuchAlgorithmException, InvalidKeyException {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeSecs = TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);
        currentTimeSecs = currentTimeSecs / inverval;
        return hotp(data, String.valueOf(currentTimeSecs).getBytes());
    }
}
