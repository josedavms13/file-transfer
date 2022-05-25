package enums;

public enum StatusCode {
    RECEIVED(201),
    DIDNT_SEND(405),
    OK(200),
    DISPATCH_FILE(203),
    FAILED(407);

    public final int code;

    StatusCode(int code) {
        this.code = code;
    }

}
