package entity;

/**
 * @author Ihor Soldatenko
 * @version 1.0.0
 */

public class Product {

    private String title;
    private String manufacturer;
    private String description;
    private double price;

    public Product(String title, String manufacturer, String description, double price) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-10s%-40s%n", "| " + title, "| " + manufacturer, "| " + price, "| " + description);
    }
}
