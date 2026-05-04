package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.UC12.*;

public class UC12Test {

    private static final double EPS = 0.01;

    @Test
    void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.value, EPS);
    }

    @Test
    void testSubtraction_Negative() {

        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(-5.0, result.value, EPS);
    }

    @Test
    void testDivision_SameUnit() {

        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPS);
    }

    @Test
    void testDivision_CrossUnit() {

        double result =
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result, EPS);
    }

    @Test
    void testDivision_ByZero() {

        assertThrows(
                ArithmeticException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET))
        );
    }

    @Test
    void testCrossCategory_Subtraction() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM))
        );
    }
}