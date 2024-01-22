package model;

// Класс для  в аккумуляторном отсеке
public class Battery extends Item {
    private int capacity;
    private int currentCharge;

    public Battery(String name, int capacity) {
        super(name);
        this.capacity = capacity;
        this.currentCharge = capacity;
    }

    public void discharge(int amount) {
        this.currentCharge -= amount;
        if (this.currentCharge < 0) {
            this.currentCharge = 0;
        }
    }

    public int getCurrentCharge() {
        return this.currentCharge;
    }

    public boolean needsMaintenance() {
        return false;
    }
}
