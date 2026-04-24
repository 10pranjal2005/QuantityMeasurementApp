package com.apps.quantitymeasurement;

/**
 * UC8 Refactored QuantityLength class
 * Delegates conversion to LengthUnit enum
 */

public class QuantityMeasurementApp {


    public static class QuantityLength {

        private final double value;

        private final LengthUnit unit;


        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;

            this.unit = unit;
        }


        // Conversion method
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double baseValue =
                    unit.convertToBaseUnit(value);

            double convertedValue =
                    targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityLength(convertedValue, targetUnit);
        }


        // Addition with explicit target unit
        public QuantityLength add(
                QuantityLength other,
                LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");


            double baseValue1 =
                    unit.convertToBaseUnit(value);

            double baseValue2 =
                    other.unit.convertToBaseUnit(other.value);


            double sum =
                    baseValue1 + baseValue2;


            double result =
                    targetUnit.convertFromBaseUnit(sum);


            return new QuantityLength(result, targetUnit);
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;


            QuantityLength other =
                    (QuantityLength) obj;


            double thisBase =
                    unit.convertToBaseUnit(value);

            double otherBase =
                    other.unit.convertToBaseUnit(other.value);


            return Double.compare(thisBase, otherBase) == 0;
        }


        @Override
        public String toString() {

            return "Quantity(" + value + ", " + unit + ")";
        }
    }


    public static void main(String[] args) {


        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);


        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);


        System.out.println(
                q1.convertTo(LengthUnit.INCHES));


        System.out.println(
                q1.add(q2, LengthUnit.FEET));


        System.out.println(
                q2.equals(
                        new QuantityLength(1.0, LengthUnit.YARDS)
                ));
    }
}