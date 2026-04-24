package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;


    @Test
    public void testAddition_TargetUnit_Feet() {

        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET);

        assertEquals(2.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_TargetUnit_Inches() {

        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES);

        assertEquals(24.0, result.value, EPSILON);
    }


    @Test
    public void testAddition_TargetUnit_Yards() {

        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(0.6666667, result.value, EPSILON);
    }


    @Test
    public void testAddition_TargetUnit_Centimeters() {

        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.INCHES),
                        new QuantityLength(1.0, LengthUnit.INCHES),
                        LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.value, EPSILON);
    }


    @Test
    public void testAddition_TargetUnit_Null() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        null));
    }
}