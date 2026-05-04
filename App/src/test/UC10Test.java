package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.UC10.*;

public class UC10Test {

    private static final double EPS = 0.01;


    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> f =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> i =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(f.equals(i));
    }


    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> g =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }


    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> f =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(12.0,
                f.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).value,
                EPS);
    }


    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(1000.0,
                kg.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).value,
                EPS);
    }


    @Test
    void testAddition() {

        Quantity<LengthUnit> f =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> i =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(2.0,
                f.add(i, LengthUnit.FEET).convertTo(LengthUnit.FEET).value,
                EPS);
    }


    @Test
    void testCrossCategory() {

        Quantity<LengthUnit> l =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> w =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(l.equals(w));
    }
}