package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.UC11.*;

public class UC11Test {

    private static final double EPS = 0.01;

    @Test
    void testEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> l =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(l.equals(ml));
    }

    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> l =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(1000.0,
                l.convertTo(VolumeUnit.MILLILITRE).convertTo(VolumeUnit.MILLILITRE).value,
                EPS);
    }

    @Test
    void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> g =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        assertEquals(3.78,
                g.convertTo(VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE).value,
                EPS);
    }

    @Test
    void testAddition_LitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.LITRE);

        assertEquals(2.0,
                result.convertTo(VolumeUnit.LITRE).value,
                EPS);
    }

    @Test
    void testCrossCategory() {

        Quantity<VolumeUnit> v =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> l =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(v.equals(l));
    }
}