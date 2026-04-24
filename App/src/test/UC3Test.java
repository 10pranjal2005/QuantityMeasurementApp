package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {


    @Test
    public void testEquality_FeetToFeet_SameValue() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }


    @Test
    public void testEquality_InchToInch_SameValue() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength q2 =
                new QuantityLength(1.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }


    @Test
    public void testEquality_FeetToInch_EquivalentValue() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inches));
    }


    @Test
    public void testEquality_InchToFeet_EquivalentValue() {

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(inches.equals(feet));
    }


    @Test
    public void testEquality_DifferentValues() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }


    @Test
    public void testEquality_NullComparison() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(null));
    }


    @Test
    public void testEquality_SameReference() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q1));
    }
}