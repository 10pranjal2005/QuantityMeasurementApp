package com.apps.quantitymeasurement;

/**
 * Standalone LengthUnit enum (UC8)
 * Responsible ONLY for conversion logic
 */

public enum LengthUnit {

    FEET(1.0),

    INCHES(1.0 / 12.0),

    YARDS(3.0),

    CENTIMETERS(1.0 / 30.48);


    private final double toFeetFactor;


    LengthUnit(double toFeetFactor) {

        this.toFeetFactor = toFeetFactor;
    }


    // Convert current unit value → feet
    public double convertToBaseUnit(double value) {

        return value * toFeetFactor;
    }


    // Convert feet → current unit
    public double convertFromBaseUnit(double baseValue) {

        return baseValue / toFeetFactor;
    }


    public double getConversionFactor() {

        return toFeetFactor;
    }
}