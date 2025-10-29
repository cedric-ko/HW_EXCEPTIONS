package ru.netology.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {
    // готовим тестовые данные
    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(1, "Товар1", 1000);
    Product product2 = new Product(2, "Товар2", 2000);
    Product product3 = new Product(3, "Товар3", 3000);
    Product product4 = new Product(4, "Товар4", 4000);
    Product product5 = new Product(5, "Товар5", 5000);

    @Test
    public void shouldRemoveById() {
        // добавляем товары в репозиторий
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        // удаляем товар из репозитория по ID, например, ID 3
        repo.remove(3);

        Product[] expected = {product1, product2, product4, product5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhileRomoveById() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product4);
        repo.add(product5);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(3); // выбрасываем исключение при удалении товара, ID которого отсутствует
        });
    }

    @Test
    public void shouldAddProduct() {
        // добавляем товары в репозиторий
        // странно выглядить, ведь подобный метод использовался в подготовке всех
        // тестов выше, но почему-то не удалось создать массив перед тестами:
        // java предлагает странный синтаксис - void new Product, и не позволяет
        // аннотацию @BeforeEach
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        Product[] expected = {product1, product2, product3, product4, product5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldThrowAlreadyExistsException() {
        // добавляем товары в репозиторий
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product3); // выбрасываем исключение при добавлении товара, ID которого уже присутствует
        });
    }
}