package pl.sda.poznan;

import pl.sda.poznan.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Program {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = factory.createEntityManager();

        Category category = new Category();
        category.setName("Laptops");

        Product asus = new Product();
        asus.setName("Asus ROG");

        Product msi = new Product();
        msi.setName("MSI");

        Product dell = new Product();
        dell.setName("DELL");

        asus.setCategory(category);
        msi.setCategory(category);
        dell.setCategory(category);


        entityManager.getTransaction().begin();

        entityManager.persist(category);
        entityManager.persist(msi);
        entityManager.persist(asus);
        entityManager.persist(dell);

        entityManager.getTransaction().commit();
    }
}
