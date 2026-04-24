package com.apps.quantitymeasurement;

/**
 * UC3: Generic Quantity Length Class using DRY Principle
 */

public class QuantityMeasurementApp {

    // Step 1: Enum for units
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }


    // Step 2: Generic QuantityLength class
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

            // Same reference
            if (this == obj)
                return true;

            // Null + type check
            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            // Convert both to feet before comparison
            double thisValueInFeet = this.unit.toFeet(this.value);
            double otherValueInFeet = other.unit.toFeet(other.value);

            return Double.compare(thisValueInFeet, otherValueInFeet) == 0;
        }


        @Override
        public int hashCode() {

            double valueInFeet = unit.toFeet(value);

            return Double.hashCode(valueInFeet);
        }
    }


    // Main method demonstration
    public static void main(String[] args) {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength q3 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength q4 =
                new QuantityLength(1.0, LengthUnit.INCH);


        System.out.println(
                "Input: Quantity(1.0, \"feet\") and Quantity(12.0, \"inches\")");

        System.out.println("Output: Equal (" + q1.equals(q2) + ")");


        System.out.println(
                "Input: Quantity(1.0, \"inch\") and Quantity(1.0, \"inch\")");

        System.out.println("Output: Equal (" + q3.equals(q4) + ")");
    }
}