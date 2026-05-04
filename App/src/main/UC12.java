package main;

public class UC12 {

    // Interface
    public interface IMeasurable {

        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }


    // LengthUnit
    public enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCHES(1.0 / 12.0);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() { return name(); }
    }


    // WeightUnit
    public enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0),
        GRAM(0.001);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() { return name(); }
    }


    // VolumeUnit
    public enum VolumeUnit implements IMeasurable {
        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double factor;

        VolumeUnit(double factor) {
            this.factor = factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() { return name(); }
    }


    // Generic Quantity Class
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


        // -------- EXISTING --------

        public Quantity<U> convertTo(U targetUnit) {

            double base = unit.convertToBaseUnit(value);

            double result = targetUnit.convertFromBaseUnit(base);

            return new Quantity<>(round(result), targetUnit);
        }


        public Quantity<U> add(Quantity<U> other, U targetUnit) {

            validate(other, targetUnit);

            double sum = toBase() + other.toBase();

            return new Quantity<>(round(targetUnit.convertFromBaseUnit(sum)), targetUnit);
        }


        // -------- UC12 NEW --------

        // Subtraction (implicit unit)
        public Quantity<U> subtract(Quantity<U> other) {
            return subtract(other, this.unit);
        }


        // Subtraction (explicit unit)
        public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

            validate(other, targetUnit);

            double result = toBase() - other.toBase();

            return new Quantity<>(round(targetUnit.convertFromBaseUnit(result)), targetUnit);
        }


        // Division (returns double)
        public double divide(Quantity<U> other) {

            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");

            if (unit.getClass() != other.unit.getClass())
                throw new IllegalArgumentException("Different categories");

            if (other.value == 0.0)
                throw new ArithmeticException("Division by zero");

            return toBase() / other.toBase();
        }


        // -------- HELPERS --------

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        private void validate(Quantity<U> other, U targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            if (unit.getClass() != other.unit.getClass())
                throw new IllegalArgumentException("Different categories");
        }

        private double round(double v) {
            return Math.round(v * 100.0) / 100.0;
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Quantity<?> other = (Quantity<?>) obj;

            if (unit.getClass() != other.unit.getClass())
                return false;

            return Double.compare(toBase(), other.toBase()) == 0;
        }


        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.getUnitName() + ")";
        }
    }


    // MAIN
    public static void main(String[] args) {

        Quantity<LengthUnit> f =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> i =
                new Quantity<>(6.0, LengthUnit.INCHES);

        // Subtraction
        System.out.println(f.subtract(i)); // 9.5 feet

        // Division
        System.out.println(f.divide(new Quantity<>(2.0, LengthUnit.FEET))); // 5.0
    }
}