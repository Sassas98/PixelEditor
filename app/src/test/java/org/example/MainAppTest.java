package org.example;

import org.example.model.MyEntity;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainAppTest {

    private EntityManagerFactory emf;

    @BeforeAll
    void setupEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("myPU");
    }

    @AfterAll
    void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    void testPersistAndRetrieveEntity() {
        EntityManager em = emf.createEntityManager();

        // Persist
        em.getTransaction().begin();
        MyEntity entity = new MyEntity("JUnitName");
        em.persist(entity);
        em.getTransaction().commit();

        Long id = entity.getId();
        assertNotNull(id);

        // Retrieve
        MyEntity found = em.find(MyEntity.class, id);
        assertNotNull(found);
        assertEquals("JUnitName", found.getName());

        em.close();
    }
}
