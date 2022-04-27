package service;

import entity.Product;
import exceptions.AlreadyExistException;
import exceptions.NotFoundException;

import java.util.List;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class ChoiceService {

    GoodService service = new GoodService();

    public String getAll() {
        List<Product> goods = service.getAll();
        StringBuilder result = new StringBuilder();
        goods.forEach(result::append);
        return result.toString();
    }

    public String getByTitle(String title) throws NotFoundException {
        return service.getByTitle(title).toString();
    }

    public String add(Product product) throws AlreadyExistException {
        service.add(product);
        return product.toString();
    }

    public String edit(Product product, String title) throws NotFoundException {
        service.edit(product, title);
        return product.toString();
    }

    public String delete(String title) throws NotFoundException {
        service.delete(title);
        return "Product " + title + " deleted.";
    }
}