package org.example;

import org.example.model.MyEntity;
import org.example.model.persistence.HibernateContext;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainAppTest {

    private HibernateContext<MyEntity> db;

    @BeforeAll
    void setupEntityManagerFactory() {
        db = new HibernateContext<MyEntity>(MyEntity.class, true);
    }

    @AfterAll
    void closeEntityManagerFactory() {
        db.dispose();
    }

    @Test
    void testPersistAndRetrieveEntity() {
        MyEntity entity = new MyEntity("JUnitName");
        db.save(entity);

        Long id = entity.getId();
        assertNotNull(id);

        // Retrieve
        MyEntity found = db.load(id);
        assertNotNull(found);
        assertEquals("JUnitName", found.getName());
    }
}
