package main;

public class UC10 {

    // Step 1: Interface
    public interface IMeasurable {

        double getConversionFactor();

        double convertToBaseUnit(double value);

        double convertFromBaseUnit(double baseValue);

        String getUnitName();
    }


    // Step 2: LengthUnit
    public enum LengthUnit implements IMeasurable {

        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() {
            return name();
        }
    }


    // Step 3: WeightUnit
    public enum WeightUnit implements IMeasurable {

        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() {
            return name();
        }
    }


    // Step 4: Generic Quantity Class
    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }


        public Quantity<U> convertTo(U targetUnit) {

            double base = unit.convertToBaseUnit(value);

            double result = targetUnit.convertFromBaseUnit(base);

            return new Quantity<>(round(result), targetUnit);
        }


        public Quantity<U> add(Quantity<U> other) {

            return add(other, this.unit);
        }


        public Quantity<U> add(Quantity<U> other, U targetUnit) {

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            double sum = base1 + base2;

            double result = targetUnit.convertFromBaseUnit(sum);

            return new Quantity<>(round(result), targetUnit);
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity<?> other = (Quantity<?>) obj;

            // Prevent cross-category
            if (unit.getClass() != other.unit.getClass())
                return false;

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            return Double.compare(base1, base2) == 0;
        }


        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }


        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.getUnitName() + ")";
        }
    }


    // Main method
    public static void main(String[] args) {

        // Length
        Quantity<LengthUnit> l1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(l1.equals(l2));
        System.out.println(l1.convertTo(LengthUnit.INCHES));
        System.out.println(l1.add(l2, LengthUnit.FEET));


        // Weight
        Quantity<WeightUnit> w1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        System.out.println(w1.add(w2, WeightUnit.KILOGRAM));


        // Cross category (must be false)
        System.out.println(l1.equals(w1));
    }
}