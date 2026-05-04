package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.UC9.QuantityWeight;
import main.UC9.WeightUnit;

public class UC9Test {

    private static final double EPSILON = 1e-6;


    @Test
    void testEquality_KgToGram() {

        QuantityWeight kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight g =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }


    @Test
    void testEquality_KgToPound() {

        QuantityWeight kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight lb =
                new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(kg.equals(lb));
    }


    @Test
    void testConversion_KgToGram() {

        QuantityWeight kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.convertTo(WeightUnit.GRAM).value, EPSILON);
    }


    @Test
    void testAddition_KgPlusGram() {

        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0,
                result.convertTo(WeightUnit.KILOGRAM).value,
                EPSILON);
    }


    @Test
    void testAddition_ExplicitTarget() {

        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(
                                new QuantityWeight(1000.0, WeightUnit.GRAM),
                                WeightUnit.GRAM
                        );

        assertEquals(2000.0,
                result.convertTo(WeightUnit.GRAM).value,
                EPSILON);
    }


    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null)
        );
    }
}