package exceptions;

import entity.Product;

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
