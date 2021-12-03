package by.petrovich.computer.exception;

public class DeviceException extends Exception {
    public DeviceException() {
    }

    public DeviceException(String message) {
        super(message);
    }

    public DeviceException(Throwable cause) {
        super(cause);
    }

    public DeviceException(String message, Throwable cause) {
        super(message, cause);
    }
}
