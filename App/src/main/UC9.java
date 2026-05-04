package main;

public class UC9 {

    // WeightUnit enum (inside same file)
    public enum WeightUnit {

        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double toKgFactor;

        WeightUnit(double toKgFactor) {
            this.toKgFactor = toKgFactor;
        }

        public double toBase(double value) {
            return value * toKgFactor;
        }

        public double fromBase(double baseValue) {
            return baseValue / toKgFactor;
        }
    }


    // QuantityWeight class
    public static class QuantityWeight {

        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }


        // Convert method
        public QuantityWeight convertTo(WeightUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base = unit.toBase(value);

            double result = targetUnit.fromBase(base);

            return new QuantityWeight(result, targetUnit);
        }


        // Addition (UC6 style)
        public QuantityWeight add(QuantityWeight other) {

            return add(other, this.unit);
        }


        // Addition (UC7 style)
        public QuantityWeight add(
                QuantityWeight other,
                WeightUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Other cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            double sum = base1 + base2;

            double result = targetUnit.fromBase(sum);

            return new QuantityWeight(result, targetUnit);
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityWeight other = (QuantityWeight) obj;

            double base1 = unit.toBase(value);
            double base2 = other.unit.toBase(other.value);

            return Double.compare(base1, base2) == 0;
        }


        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }


    // Main method
    public static void main(String[] args) {

        QuantityWeight kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight g =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight lb =
                new QuantityWeight(2.20462, WeightUnit.POUND);

        // Equality
        System.out.println(kg.equals(g)); // true

        // Conversion
        System.out.println(kg.convertTo(WeightUnit.GRAM));

        // Addition
        System.out.println(kg.add(g)); // 2 kg

        System.out.println(kg.add(lb, WeightUnit.KILOGRAM));
    }
}