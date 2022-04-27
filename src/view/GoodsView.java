package view;

import service.IOGoods;

import java.util.Scanner;

import static utils.TextColour.*;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class GoodsView {


    public static int choice(Scanner scanner){
        System.out.print(ANSI_YELLOW + "Choice, what you want to do:\n" +
                "1. View all goods in the database;\n" +
                "2. Find the product by its title;\n" +
                "3. Add new product;\n" +
                "4. Edit the product by name;\n" +
                "5. Delete the product by name;\n" +
                "6. Initial database with 5 example rows;\n" +
                "9. Quite.\n" +
                "Make your choice: ");
        return IOGoods.getInt(scanner);
    }

    public static void showDialog(String dialog){

        System.out.println(ANSI_GREEN + dialog);
    }

    public static void showGoods(String goods, String title){

        System.out.println(ANSI_BLUE + "============================ " + title + " ============================");
        String header = String.format(ANSI_PURPLE + "%-40s%-20s%-10s%-40s%n", "| Title", "| Manufacturer", "| Price", "| Description");
        header = "\033[1m" + header + "\033[0m";
        System.out.print(header);
        System.out.println(ANSI_BLUE + goods);
    }

    public static void showExceptions(String exception){

        System.out.println(ANSI_RED + exception);
    }
}
