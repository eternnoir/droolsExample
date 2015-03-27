package org.enoir.droolstest;

/**
 * Created by frank on 2015/3/26.
 */
public class Message {
    private String message;
    public static String GOODBYE = "1" ;
    public static String HELLO="0";
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
