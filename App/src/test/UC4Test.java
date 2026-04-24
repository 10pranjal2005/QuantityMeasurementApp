package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {


    @Test
    public void testEquality_YardToYard_SameValue() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength q2 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(q1.equals(q2));
    }


    @Test
    public void testEquality_YardToFeet_EquivalentValue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }


    @Test
    public void testEquality_YardToInches_EquivalentValue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches));
    }


    @Test
    public void testEquality_CentimeterToInches_EquivalentValue() {

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        assertTrue(cm.equals(inch));
    }


    @Test
    public void testEquality_DifferentValues() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength q2 =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }


    @Test
    public void testEquality_SameReference() {

        QuantityLength q1 =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        assertTrue(q1.equals(q1));
    }


    @Test
    public void testEquality_NullComparison() {

        QuantityLength q1 =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        assertFalse(q1.equals(null));
    }
}
