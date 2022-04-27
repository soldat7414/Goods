package exceptions;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class NotFoundException extends Exception{

    public NotFoundException(String message){
        super(message);
    }
}
