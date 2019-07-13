package pl.sda.jp.miniblogwa16.user;

public class UserAlreadyExistsException extends Throwable {

    public UserAlreadyExistsException(String message) {
super (message);
    }
}
