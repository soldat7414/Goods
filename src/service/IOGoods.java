package service;

import entity.Product;
import exceptions.EmptyInputException;
import exceptions.NotNumberException;

import java.util.Scanner;

import static utils.TextColour.ANSI_GREEN;
import static utils.TextColour.ANSI_RED;

/**
 * @author Soldatenko Ihor
 * @version 1.0.0
 */

public class IOGoods {

    private static String getString(Scanner scanner) throws EmptyInputException {
        if (!scanner.hasNextLine()) throw new EmptyInputException("You didn't enter anything! Try again!");
        return scanner.nextLine();
    }

    private static int getI(Scanner scanner) throws NotNumberException {

        int result;
        if (scanner.hasNextInt()) result = scanner.nextInt();
        else throw new NotNumberException("That what you enter and it isn't number!", scanner.nextLine().trim());
        return result;
    }

    private static double getD(Scanner scanner) throws NotNumberException, NumberFormatException {

        double result;
        if (scanner.hasNextDouble()) result = scanner.nextDouble();
        else throw new NotNumberException("That what you enter and it isn't number!", scanner.nextLine().trim());
        if (result <= 0) throw new NumberFormatException("Price can't be zero or lover! Try again.");
        return result;
    }

    public static String getStr(Scanner scanner) {

        String str = "";
        do {
            try {
                str = getString(scanner);
            } catch (EmptyInputException ex) {
                System.out.println(ANSI_RED + ex.getMessage());
            }
        } while (str.length() < 1);
        return str;
    }

    public static int getInt(Scanner scanner) {

        int result = 0;
        do {
            try {
                result = getI(scanner);
            } catch (NotNumberException ex) {
                System.out.println(ANSI_RED + "'" + ex.getStr() + "'" + " - " + ex.getMessage());
            }
        } while (result < 1);
        return result;
    }

    public static double getDouble(Scanner scanner) {

        double result = 0;
        do {
            try {
                result = getD(scanner);
            } catch (NotNumberException ex) {
                System.out.println(ANSI_RED + "'" + ex.getStr() + "'" + " - " + ex.getMessage());
            } catch (NumberFormatException nf) {
                System.out.println(ANSI_RED + nf.getMessage());
            }
        } while (result < 1);
        return result;
    }


    public static Product getProduct(Scanner scanner) {

        System.out.print(ANSI_GREEN + "Enter title: ");
        String title = getStr(scanner);
        System.out.print("Enter manufacturer: ");
        String manufacturer = getStr(scanner);
        System.out.print("Enter description: ");
        String description = getStr(scanner);
        System.out.print("Enter price: ");
        double price = getDouble(scanner);
        return new Product(title, manufacturer, description, price);
    }
}

