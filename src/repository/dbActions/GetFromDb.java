package repository.dbActions;

import entity.Product;
import exceptions.NotFoundException;
import repository.Properties;

import java.util.List;
import java.util.Optional;

import static repository.RWGoods.read;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class GetFromDb {

    static String sqlQuery;

    /**
     * method to get all goods from the database
     * @return List<Product> with all entries or null in case the connection was failed
     */
    public static List<Product> getAll(){
        sqlQuery = "SELECT " + Properties.COLUMNS + " FROM " + Properties.TABLE_NAME;
        return read(sqlQuery);
    }

    /**
     * method to get a product from the database by given title
     * @param title can't be literal {@null}
     * @return Optional with a product if it was found, and empty Optional in all other cases
     * @throws NotFoundException in case if database don't contains any product with given title
     */
    public static Optional<Product> getByTitle(String title) throws NotFoundException {
        Optional<Product> product = Optional.empty();
        sqlQuery = "SELECT " + Properties.COLUMNS + " FROM " + Properties.TABLE_NAME +
                " WHERE title = '" + title + "'";
        List<Product> goods = read(sqlQuery);
        if (goods != null){
            if(goods.isEmpty()) throw new NotFoundException("There isn't product with given title in database!");
            product = Optional.of(goods.get(0));
        }
        return product;
    }
}
