package controller;

import entity.Product;
import exceptions.AlreadyExistException;
import exceptions.NotFoundException;
import repository.dbActions.WriteToDb;
import service.ChoiceService;
import service.GoodService;
import service.IOGoods;

import java.util.Scanner;

import static view.GoodsView.*;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class GoodsController {

    private static final ChoiceService service = new ChoiceService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        int ch;
        do {
            ch = choice(scanner);
            switch (ch) {
                case 1 -> showGoods(service.getAll(), "All goods in the database:");
                case 2 -> {
                    showDialog("Enter the title:");
                    String title = IOGoods.getStr(scanner);
                    try {
                        showGoods(service.getByTitle(title), "Product with title: " + title);
                    } catch (NotFoundException ex) {
                        showExceptions(ex.getMessage());
                    }
                }
                case 3 -> {
                    showDialog("Enter the title: ");
                    String title = IOGoods.getStr(scanner);
                    showDialog("Enter the manufacturer: ");
                    String manufacturer = IOGoods.getStr(scanner);
                    showDialog("Enter the description: ");
                    String description = IOGoods.getStr(scanner);
                    showDialog("Enter the price: ");
                    double price = IOGoods.getDouble(scanner);
                    Product product = new Product(title, manufacturer, description, price);
                    boolean mark;
                    do {
                        try {
                            showGoods(service.add(product), "Product added:");
                            mark = true;
                        } catch (AlreadyExistException ex) {
                            showExceptions(ex.getMessage() + "\n" + ex.getProduct().toString());
                            showDialog("Do you want change the title? (y - yes, else - no)\n Enter your choice: ");
                            mark = !IOGoods.getStr(scanner).equals("y");
                            if (!mark) {
                                showDialog("Enter else title:");
                                product.setTitle(IOGoods.getStr(scanner));
                            }
                        }

                    } while (!mark);
                }
                case 4 -> {
                    showDialog("Enter title:");
                    String title = IOGoods.getStr(scanner);
                    Product product;
                    try {
                        product = new GoodService().getByTitle(title);
                        showDialog("You are about to edit the following product:\n" + product.toString());
                        showDialog("Do you want to edit the title? (y - yes, else - no): ");
                        if (IOGoods.getStr(scanner).equals("y")) {
                            showDialog("Enter else title:");
                            product.setTitle(IOGoods.getStr(scanner));
                        }
                        showDialog("Do you want to edit the manufacturer? (y - yes, else - no): ");
                        if (IOGoods.getStr(scanner).equals("y")) {
                            showDialog("Enter else manufacturer:");
                            product.setManufacturer(IOGoods.getStr(scanner));
                        }
                        showDialog("Do you want to edit the price? (y - yes, else - no): ");
                        if (IOGoods.getStr(scanner).equals("y")) {
                            showDialog("Enter else price:");
                            product.setPrice(IOGoods.getDouble(scanner));
                        }
                        showDialog("Do you want to edit the description? (y - yes, else - no): ");
                        if (IOGoods.getStr(scanner).equals("y")) {
                            showDialog("Enter else description:");
                            product.setDescription(IOGoods.getStr(scanner));
                        }
                        showGoods(service.edit(product, title), "The product was edited: ");
                    } catch (NotFoundException ex) {
                        showExceptions(ex.getMessage());
                    }
                }
                case 5 -> {
                    showDialog("Enter title: ");
                    String title = IOGoods.getStr(scanner);
                    try {
                        showGoods(service.delete(title), "");
                    } catch (NotFoundException ex) {
                        showExceptions(ex.getMessage());
                    }
                }
                case 6 -> service.initialDb();
                default -> {
                    if (ch != 9) showExceptions("There is no such function yet, select from the list!");
                }
                case 200 -> new WriteToDb().clean();
            }
            if (ch != 9){
                showDialog("Do you want continue? (y - yes, other - no)");
                if(!IOGoods.getStr(scanner).equals("y")) ch = 9;
            }
        }
        while (ch != 9);
        showDialog("\033[1m" + "======= Goodbye! =======" + "\033[0m");
        scanner.close();
    }
}