package pl.sda.poznan;

import pl.sda.poznan.model.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = factory.createEntityManager();

        seedData(entityManager);

        Query query = entityManager.createQuery("select p from Product p");
        List resultList = query.getResultList();

        Query getByIdQuery = entityManager.createQuery("select p from Product p where p.id = :productId");
        getByIdQuery.setParameter("productId", 2L);
        Product singleResult = (Product) getByIdQuery.getSingleResult();

        Query maxPrice = entityManager.createQuery("select max (p.price) from Product p");

        Object singleResult1 = maxPrice.getSingleResult();


        Query query1 = entityManager.createQuery("select price,     name from Product");
        List resultList1 = query1.getResultList();


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot);
        criteriaQuery.where(criteriaBuilder.equal(productRoot.get("id"),criteriaBuilder.parameter(Long.class,"productId")));
        Query query3 = entityManager.createQuery(criteriaQuery);
        query3.setParameter("productId",2L);
        Object singleResult2 = query3.getResultList();
    }

    private static void seedData(EntityManager entityManager) {
        Category category = new Category();
        category.setName("Laptops");

        Product asus = new Product();
        asus.setName("Asus ROG");
        asus.setPrice(3000D);

        Product msi = new Product();
        msi.setName("MSI");
        msi.setPrice(2500D);

        Product dell = new Product();
        dell.setName("DELL");
        dell.setPrice(2200D);

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
