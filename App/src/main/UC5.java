package com.apps.quantitymeasurement;

/**
 * UC5: Unit-to-Unit Conversion Support
 */

public class QuantityMeasurementApp {

    // Enum with conversion factors relative to FEET (base unit)
    public enum LengthUnit {

        FEET(1.0),

        INCHES(1.0 / 12.0),

        YARDS(3.0),

        CENTIMETERS(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }


    // Generic QuantityLength class
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }


        // Instance conversion method
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double valueInFeet = unit.toFeet(value);

            double convertedValue = targetUnit.fromFeet(valueInFeet);

            return new QuantityLength(convertedValue, targetUnit);
        }


        // Static conversion API (assignment requirement)
        public static double convert(
                double value,
                LengthUnit source,
                LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            double valueInFeet = source.toFeet(value);

            return target.fromFeet(valueInFeet);
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            double thisFeet = unit.toFeet(value);
            double otherFeet = other.unit.toFeet(other.value);

            return Double.compare(thisFeet, otherFeet) == 0;
        }


        @Override
        public String toString() {

            return "Quantity(" + value + ", " + unit + ")";
        }
    }


    // Demo main method
    public static void main(String[] args) {

        System.out.println(
                "convert(1.0, FEET, INCHES) → "
                        + QuantityLength.convert(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));

        System.out.println(
                "convert(3.0, YARDS, FEET) → "
                        + QuantityLength.convert(3.0,
                        LengthUnit.YARDS,
                        LengthUnit.FEET));

        System.out.println(
                "convert(36.0, INCHES, YARDS) → "
                        + QuantityLength.convert(36.0,
                        LengthUnit.INCHES,
                        LengthUnit.YARDS));

        System.out.println(
                "convert(1.0, CENTIMETERS, INCHES) → "
                        + QuantityLength.convert(1.0,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES));
    }
}