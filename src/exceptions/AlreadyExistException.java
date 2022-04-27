package exceptions;

import entity.Product;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class AlreadyExistException extends Exception {

    Product product;

    public AlreadyExistException(String message, Product product){
        super(message);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
