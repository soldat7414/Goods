package repository.dbActions;

import entity.Product;
import exceptions.AlreadyExistException;
import exceptions.NotFoundException;
import repository.Properties;
import repository.RWGoods;

import java.util.Optional;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class WriteToDb{

    String sqlQuery = "";

    /**
     * method to save the product in the database
     * @param product can't be literal {@null}
     * @return Optional with the added product,
     * or empty Optional in case the added was failure
     * @throws AlreadyExistException in case, if a product with same title already exist in the database
     */
    public Optional<Product> save(Product product) throws AlreadyExistException{
        Optional<Product> productDb;
        try{
            productDb = new GetFromDb().getByTitle(product.getTitle());
            if(productDb.isPresent()) throw new AlreadyExistException("The product with same title " +
                    "already exist in the database:", productDb.get());
        }catch (NotFoundException ignored){  }

        sqlQuery = "INSERT INTO " + Properties.TABLE_NAME + " (" + Properties.COLUMNS + ") " +
                "VALUES ('" + product.getTitle() + "', '" + product.getManufacturer() + "', '" +
                product.getDescription() + "', '" + product.getPrice() + "')";
        return RWGoods.write(product, sqlQuery);
    }

    /**
     * method to edit the product in the database by given title
     * @param product contains the changes, cant be literal {@null}
     * @param title can't be literal {@null}
     * @return edited product
     * @throws NotFoundException in case if database don't contains any product with given title
     */
    public Optional<Product> editByTitle(Product product, String title) throws NotFoundException{
        Optional<Product> productDb = new GetFromDb().getByTitle(title);
        sqlQuery = "UPDATE " + Properties.TABLE_NAME + " SET title = '" + product.getTitle() +
                "', manufacturer = '" + product.getManufacturer() + "', description = '" + product.getDescription() +
                "', price = '" + product.getPrice() + "' WHERE title = '" + title + "'";
        return RWGoods.write(product, sqlQuery);
    }

    /**
     * metod to delete the product with given title from the database
     * @param title can't be literal {@null}
     * @return the title of deleted product
     * @throws NotFoundException in case if database don't contains any product with given title
     */
    public String delete(String title) throws NotFoundException{
        Optional<Product> productDb = new GetFromDb().getByTitle(title);
        sqlQuery = "DELETE FROM " + Properties.TABLE_NAME + " WHERE title = '" + title + "'";
        RWGoods.write(new Product(title, "","",0), sqlQuery);
        return title;
    }

    public void clean(){
        sqlQuery = "DELETE FROM " + Properties.TABLE_NAME;
        RWGoods.write(new Product("title", "","",0), sqlQuery);
    }
}
