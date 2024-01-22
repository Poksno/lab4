package model;

public class ChemicalEquipment extends Item {
    private boolean isActive;

    public ChemicalEquipment(String name) {
        super(name);
        this.isActive = false;
    }

    public void activate() {
        if (!isActive) {
            isActive = true;
            System.out.println(getName() + " активировано для очистки воздуха.");
        } else {
            System.out.println(getName() + " уже активно.");
        }
    }

    public void deactivate() {
        if (isActive) {
            isActive = false;
            System.out.println(getName() + " деактивировано.");
        } else {
            System.out.println(getName() + " уже неактивно.");
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
