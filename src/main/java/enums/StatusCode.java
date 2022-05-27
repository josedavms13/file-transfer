package enums;

import java.util.Arrays;

public enum StatusCode {
    RECEIVED(201, "Received"),
    DIDNT_SEND(405, "Didn't send"),
    READY_TO_RECEIVE(300, "Already to receive"),
    OK(200, "OK"),
    DISPATCH_FILE(203, "Dispatch File"),
    FAILED(407, "Failed");

    public final int code;
    public final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
