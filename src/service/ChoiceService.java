package service;

import entity.Product;
import exceptions.AlreadyExistException;
import exceptions.NotFoundException;

import java.util.ArrayList;
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

    public void initialDb() {
        List<Product> goods = new ArrayList<>();
        goods.add(new Product("Кофе в зернах Julius Expert", "Julius Meinl", "кафе Эксперт " +
                "Смесь из превосходных, ароматных сортов арабики Бразилии и Центральной Америки.", 1230));
        goods.add(new Product("Кофе в капсулах Julius meinl", "Julius", "Используется в" +
                " кофемашинах системы Nespresso.", 170.2));
        goods.add(new Product("Растворимый кофе ТМ \"Jacobs Monarch\"", "Jacobs",
                "Растворимый сублимированный кофе в гранулах Jacobs Monarch (Якобс Монарх) " +
                        "в эконом-пакете 400 грамм.", 275.3));
        goods.add(new Product("Кофе Movenpick Der", "Movenpick", "Der Himmlische - смесь" +
                " из зерен высококачественной арабики.", 184));
        goods.add(new Product("Кофе \"Ambassador Blue Label\"", "Ambassador",
                " растворимый кофе с оригинальный вкусом и нежным ароматом.", 255));
        goods.add(new Product("Кава Лавацца крема", "Lavazza", "Кава Лавацца крема густо" +
                " Lavazza crema gusto 250g 18шт/ящ Мелена Виробник - Італія", 83.19));
        goods.add(new Product("Кофе зерновой Zavida Hazelnut Vanilla", "Zavida", "Получайте " +
                "удовольствие с каждой выпитой чашкой.", 949));
        goods.add(new Product("Кофе молотый Konigs Oro Crema", "Konigs Kaffee", "Идеально " +
                "подходит для полностью ароматного эспрессо", 95));
        goods.forEach((item)-> {
            try {
                service.add(item);
            } catch (AlreadyExistException ignored) {
            }
        });
    }
}