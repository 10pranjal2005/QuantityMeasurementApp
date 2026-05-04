package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;


    @Test
    void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON
        );
    }


    @Test
    void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON
        );
    }


    @Test
    void testQuantityEquality_FeetToInches() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(q1.equals(q2));
    }


    @Test
    void testAddition_WithTargetUnit() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ).add(

                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                LengthUnit.INCHES
                        ),

                        LengthUnit.FEET
                );


        assertEquals(2.0, result.value, EPSILON);
    }
}