package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.UC13.*;

public class UC13Test {

    private static final double EPS = 0.01;

    @Test
    void testAdd() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES),
                                LengthUnit.FEET);

        assertEquals(2.0, result.value, EPS);
    }

    @Test
    void testSubtract() {

        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.value, EPS);
    }

    @Test
    void testDivide() {

        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPS);
    }

    @Test
    void testCrossCategory() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM))
        );
    }

    @Test
    void testDivideByZero() {

        assertThrows(
                ArithmeticException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET))
        );
    }
}