package Views;

public class ViewFilterException extends Exception {
    public String msg;

    public ViewFilterException(String msg) {
        this.msg = msg;
    }
}
