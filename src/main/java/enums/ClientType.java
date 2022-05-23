package enums;

public enum ClientType {
    RECEIVER("receiver"),
    EMMITER_CLI("emitter_cli"),
    SERVER("server"),
    EMMITER("emitter");


    public final String type;

    ClientType(String type) {
        this.type = type;
    }
}
