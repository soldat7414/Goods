package service;

import entity.Product;
import exceptions.AlreadyExistException;
import exceptions.NotFoundException;
import repository.dbActions.GetFromDb;
import repository.dbActions.WriteToDb;

import java.util.List;
import java.util.Optional;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class GoodService {
    GetFromDb gRepo = new GetFromDb();
    WriteToDb wRepo = new WriteToDb();

    public List<Product> getAll() {
        return gRepo.getAll();
    }

    public Product getByTitle(String title) throws NotFoundException {
        Optional<Product> result =  gRepo.getByTitle(title);
        return result.orElse(null);
    }

    public void add(Product product) throws AlreadyExistException {
        wRepo.save(product);
    }

    public void edit(Product product, String title) throws NotFoundException {
        wRepo.editByTitle(product, title);
    }

    public void delete(String title) throws NotFoundException {
        wRepo.delete(title);
    }
}
