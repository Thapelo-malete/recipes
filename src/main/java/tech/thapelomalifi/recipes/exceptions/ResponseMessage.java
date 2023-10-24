package tech.thapelomalifi.recipes.exceptions;

import java.time.LocalDateTime;

public class ResponseMessage {
    private String message;
    private int status;
    private LocalDateTime time;

    public ResponseMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
