package com.apps.quantitymeasurement;

/**
 * UC7: Addition with Target Unit Specification
 */

public class QuantityMeasurementApp {

    // Enum with conversion factors relative to FEET
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


        // UC6 method (implicit result unit = first operand)
        public QuantityLength add(QuantityLength other) {

            return add(this, other, this.unit);
        }


        // UC7 method (explicit target unit)
        public static QuantityLength add(
                QuantityLength length1,
                QuantityLength length2,
                LengthUnit targetUnit) {

            if (length1 == null || length2 == null)
                throw new IllegalArgumentException("Operands cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double feetValue1 =
                    length1.unit.toFeet(length1.value);

            double feetValue2 =
                    length2.unit.toFeet(length2.value);

            double sumFeet =
                    feetValue1 + feetValue2;

            double resultValue =
                    targetUnit.fromFeet(sumFeet);

            return new QuantityLength(resultValue, targetUnit);
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other =
                    (QuantityLength) obj;

            double thisFeet =
                    unit.toFeet(value);

            double otherFeet =
                    other.unit.toFeet(other.value);

            return Double.compare(thisFeet, otherFeet) == 0;
        }


        @Override
        public String toString() {

            return "Quantity(" + value + ", " + unit + ")";
        }
    }


    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);


        System.out.println(
                QuantityLength.add(
                        feet,
                        inches,
                        LengthUnit.FEET));

        System.out.println(
                QuantityLength.add(
                        feet,
                        inches,
                        LengthUnit.INCHES));

        System.out.println(
                QuantityLength.add(
                        feet,
                        inches,
                        LengthUnit.YARDS));
    }
}