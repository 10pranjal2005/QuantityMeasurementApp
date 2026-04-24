package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;


    @Test
    public void testConversion_FeetToInches() {

        double result =
                QuantityLength.convert(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES);

        assertEquals(12.0, result, EPSILON);
    }


    @Test
    public void testConversion_InchesToFeet() {

        double result =
                QuantityLength.convert(24.0,
                        LengthUnit.INCHES,
                        LengthUnit.FEET);

        assertEquals(2.0, result, EPSILON);
    }


    @Test
    public void testConversion_YardsToInches() {

        double result =
                QuantityLength.convert(1.0,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES);

        assertEquals(36.0, result, EPSILON);
    }


    @Test
    public void testConversion_CentimetersToInches() {

        double result =
                QuantityLength.convert(2.54,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES);

        assertEquals(1.0, result, EPSILON);
    }


    @Test
    public void testConversion_ZeroValue() {

        double result =
                QuantityLength.convert(0.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES);

        assertEquals(0.0, result);
    }


    @Test
    public void testConversion_NegativeValue() {

        double result =
                QuantityLength.convert(-1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES);

        assertEquals(-12.0, result, EPSILON);
    }


    @Test
    public void testConversion_InvalidUnit_Throws() {

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0,
                        null,
                        LengthUnit.FEET));
    }
}