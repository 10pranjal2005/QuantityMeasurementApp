package main;

public class UC11 {

    // Interface (same as UC10)
    public interface IMeasurable {

        double getConversionFactor();
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }


    // LengthUnit (same)
    public enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCHES(1.0 / 12.0);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() { return factor; }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() { return name(); }
    }


    // WeightUnit (same)
    public enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0),
        GRAM(0.001);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() { return factor; }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() { return name(); }
    }


    // ✅ NEW — VolumeUnit
    public enum VolumeUnit implements IMeasurable {

        LITRE(1.0),
        MILLILITRE(0.001),
        GALLON(3.78541);

        private final double factor;

        VolumeUnit(double factor) {
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


    // Generic Quantity class (same as UC10)
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

            if (unit.getClass() != other.unit.getClass())
                return false;

            double base1 = unit.convertToBaseUnit(value);
            double base2 = other.unit.convertToBaseUnit(other.value);

            return Double.compare(base1, base2) == 0;
        }


        private double round(double v) {
            return Math.round(v * 100.0) / 100.0;
        }


        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.getUnitName() + ")";
        }
    }


    // Main demo
    public static void main(String[] args) {

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> v3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        // Equality
        System.out.println(v1.equals(v2)); // true

        // Conversion
        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE));

        // Addition
        System.out.println(v1.add(v2, VolumeUnit.LITRE));

        // Cross-category check
        Quantity<LengthUnit> l =
                new Quantity<>(1.0, LengthUnit.FEET);

        System.out.println(v1.equals(l)); // false
    }
}