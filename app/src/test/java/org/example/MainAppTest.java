package org.example;

import org.example.controller.PixelGetter;
import org.example.controller.PixelHandler;
import org.example.controller.PixelSetter;
import org.example.controller.SimpleColors2D32PixelGetter;
import org.example.controller.SimpleColors2D32PixelSetter;
import org.example.model.entity.Pixel;
import org.example.model.entity.PixelColor;
import org.example.model.persistence.HibernateContext;
import org.example.model.utility.Position2D;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainAppTest {

    private PixelGetter<Position2D, PixelColor> getter;
    private PixelSetter<Position2D, PixelColor> setter;

    @BeforeAll
    void setupEntityManagerFactory() {
        HibernateContext<Pixel> db = new HibernateContext<Pixel>(Pixel.class, true);
        setter = new SimpleColors2D32PixelSetter(db);
        getter = new SimpleColors2D32PixelGetter(db);
    }

    @AfterAll
    void closeEntityManagerFactory() {
        setter.dispose();
    }

    @Test
    void testPersistAndRetrieveEntity() {
        assertEquals(PixelColor.WHITE, getter.getColorAt(new Position2D(0, 0)));
        assertTrue(setter.setPixelColor(new Position2D(0, 0), PixelColor.RED));
        assertEquals(PixelColor.RED, getter.getColorAt(new Position2D(0, 0)));
    }
}
