package repository;

import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.DBConnector.getConnection;
import static utils.TextColour.*;

public class RWGoods {

    public static Optional<Product> wright(Product product, String sqlQuery){
        Optional<Product> result = Optional.empty();
        try (Statement stmt = getConnection().createStatement()){
            try{
                stmt.executeUpdate(sqlQuery);
                System.out.println(ANSI_GREEN + "Row added");
                result = Optional.of(product);
                return result;
            } catch (SQLException ex){
                System.out.println(ANSI_RED + "row didn't add because: " + ex.getMessage());
                return result ;
            }
        } catch (SQLException exc){
            System.out.println(ANSI_RED + "statement didn't create because: " + exc.getMessage());
            return result;
        }
    }

    public static List<Product> read(String sqlQuery){
        List<Product> plants = new ArrayList<>();
        try(Statement stmt = getConnection().createStatement()){
            try(ResultSet rs = stmt.executeQuery(sqlQuery)){
                while(rs.next()){
                    plants.add(new Product(
                            rs.getString("title"),
                            rs.getString("manufacturer"),
                            rs.getString("description"),
                            rs.getDouble("price")
                    ));
                }
                return plants;
            }catch (SQLException ex){
                System.out.println(ANSI_RED + "Didn't get result because: " + ex.getMessage());
                return null;
            }
        }catch (SQLException exc){
            System.out.println(ANSI_RED + "statement didn't create because: " + exc.getMessage());
            return null;
        }
    }

}
