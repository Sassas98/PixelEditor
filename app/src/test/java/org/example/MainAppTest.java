package org.example;

import org.example.controller.PixelHandler;
import org.example.controller.SimpleColors32PixelHandler;
import org.example.model.entity.Pixel;
import org.example.model.persistence.HibernateContext;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainAppTest {

    private PixelHandler handler;

    @BeforeAll
    void setupEntityManagerFactory() {
        HibernateContext<Pixel> db = new HibernateContext<Pixel>(Pixel.class, true);
        handler = new SimpleColors32PixelHandler(db); 
    }

    @AfterAll
    void closeEntityManagerFactory() {
        handler.dispose();
    }

    @Test
    void testPersistAndRetrieveEntity() {
        assertEquals("white", handler.getColorAt(0, 0));
        assertTrue(handler.setPixelColor(0, 0, "red"));
        assertEquals("red", handler.getColorAt(0, 0));
    }
}
