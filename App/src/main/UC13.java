package main;

import java.util.function.DoubleBinaryOperator;

public class UC13 {

    // Interface
    public interface IMeasurable {
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }


    // LengthUnit
    public enum LengthUnit implements IMeasurable {
        FEET(1.0), INCHES(1.0 / 12.0);

        private final double factor;

        LengthUnit(double factor) { this.factor = factor; }

        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double v) { return v / factor; }
        public String getUnitName() { return name(); }
    }


    // WeightUnit
    public enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0), GRAM(0.001);

        private final double factor;

        WeightUnit(double factor) { this.factor = factor; }

        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double v) { return v / factor; }
        public String getUnitName() { return name(); }
    }


    // VolumeUnit
    public enum VolumeUnit implements IMeasurable {
        LITRE(1.0), MILLILITRE(0.001);

        private final double factor;

        VolumeUnit(double factor) { this.factor = factor; }

        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double v) { return v / factor; }
        public String getUnitName() { return name(); }
    }


    // 🔥 CENTRAL ENUM (UC13 CORE)
    private enum Operation {

        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator op;

        Operation(DoubleBinaryOperator op) {
            this.op = op;
        }

        public double compute(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }


    // Generic Quantity
    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (unit == null) throw new IllegalArgumentException("Unit null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }


        // ---------- CENTRAL HELPER ----------

        private double performBaseArithmetic(
                Quantity<U> other,
                Operation operation) {

            validate(other);

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            return operation.compute(base1, base2);
        }


        private void validate(Quantity<U> other) {

            if (other == null)
                throw new IllegalArgumentException("Other null");

            if (unit.getClass() != other.unit.getClass())
                throw new IllegalArgumentException("Different category");

            if (!Double.isFinite(other.value))
                throw new IllegalArgumentException("Invalid number");
        }


        // ---------- PUBLIC METHODS ----------

        public Quantity<U> add(Quantity<U> other, U target) {

            double result = performBaseArithmetic(other, Operation.ADD);

            return new Quantity<>(
                    round(target.convertFromBaseUnit(result)), target);
        }


        public Quantity<U> subtract(Quantity<U> other) {

            double result = performBaseArithmetic(other, Operation.SUBTRACT);

            return new Quantity<>(
                    round(unit.convertFromBaseUnit(result)), unit);
        }


        public double divide(Quantity<U> other) {

            return performBaseArithmetic(other, Operation.DIVIDE);
        }


        private double round(double v) {
            return Math.round(v * 100.0) / 100.0;
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity<?> o = (Quantity<?>) obj;

            if (unit.getClass() != o.unit.getClass()) return false;

            return Double.compare(
                    unit.convertToBaseUnit(value),
                    o.unit.convertToBaseUnit(o.value)) == 0;
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

        System.out.println(f.subtract(i));   // 9.5
        System.out.println(f.divide(new Quantity<>(2.0, LengthUnit.FEET))); // 5
    }
}