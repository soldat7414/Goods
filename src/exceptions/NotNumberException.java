package exceptions;

/**
 * @author Soldatenko Ihor
 * @version 1.0.0
 */

public class NotNumberException extends Exception{

    String str;

    public NotNumberException(String message, String str){
        super(message);
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
