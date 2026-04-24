package com.apps.quantitymeasurement;

/**
 * UC4: Extended Unit Support
 * Added YARDS and CENTIMETERS support
 */

public class QuantityMeasurementApp {

    // Updated enum with new units
    public enum LengthUnit {

        FEET(1.0),

        INCHES(1.0 / 12.0),

        YARDS(3.0),

        CENTIMETERS(0.393701 / 12.0);


        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }


    // Generic QuantityLength class (same as UC3)
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            double thisFeet = this.unit.toFeet(this.value);

            double otherFeet = other.unit.toFeet(other.value);

            return Double.compare(thisFeet, otherFeet) == 0;
        }


        @Override
        public int hashCode() {

            double valueFeet = unit.toFeet(value);

            return Double.hashCode(valueFeet);
        }
    }


    // Demo main method
    public static void main(String[] args) {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength q2 =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength q3 =
                new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength q4 =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength q5 =
                new QuantityLength(0.393701, LengthUnit.INCHES);


        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(36.0, INCHES)");
        System.out.println("Output: Equal (" + q1.equals(q3) + ")");

        System.out.println("Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)");
        System.out.println("Output: Equal (" + q4.equals(q5) + ")");
    }
}