package model;

public class FoodItem extends Item {
    private double weight;

    public FoodItem(String name, double weight) {
        super(name);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void consume(double amount) {
        if (amount < 0 || amount > weight) {
            throw new IllegalArgumentException("Недопустимое количество для потребления.");
        }
        weight -= amount;
        System.out.println(amount + " кг " + getName() + " потреблено. Осталось " + weight + " кг.");
    }
}
