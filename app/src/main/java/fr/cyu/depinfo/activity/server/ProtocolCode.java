package fr.cyu.depinfo.activity.server;

public enum ProtocolCode {
    DATAOK(0x10),
    FORMATOK(0x11),
    ACCESSOK(0x12),
    BADDATA(0x20),
    BADFORMAT(0x21),
    BADACCESS(0x22),
    ROOMID(0x30),
    BUILDINGID(0x31),
    PERSONID(0x32),
    NONCE(0x33),
    PASSWD(0x34);

    private final Integer code;

    ProtocolCode(Integer code) {
        this.code = code;
    }

    public static ProtocolCode fromCode(Integer code) {
        return switch (code) {
            case 0x10 -> DATAOK;
            case 0x11 -> FORMATOK;
            case 0x12 -> ACCESSOK;
            case 0x20 -> BADDATA;
            case 0x21 -> BADFORMAT;
            case 0x22 -> BADACCESS;
            case 0x30 -> ROOMID;
            case 0x31 -> BUILDINGID;
            case 0x32 -> PERSONID;
            case 0x33 -> NONCE;
            case 0x34 -> PASSWD;
            default -> null;
        };
    }

    public Integer getCode() {
        return code;
    }
}
