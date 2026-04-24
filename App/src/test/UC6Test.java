package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;


    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(3.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = feet.add(inches);

        assertEquals(2.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = inches.add(feet);

        assertEquals(24.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = yard.add(feet);

        assertEquals(2.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_WithZero() {

        QuantityLength feet =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength zero =
                new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = feet.add(zero);

        assertEquals(5.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_NullOperand() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> feet.add(null)
        );
    }
}