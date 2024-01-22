package model;

import java.util.ArrayList;
import java.util.List;

public class Compartment<T> {
    private String name;
    private List<T> items;

    public Compartment(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    public void activateEquipment(String equipmentName) {
        if (equipmentName.equals("CO2 Scrubber")) {
            System.out.println("CO2 Scrubber активирован в отсеке " + name + ".");
        } else if (equipmentName.equals("Oxygen Generator")) {
            System.out.println("Oxygen Generator активирован в отсеке " + name + ".");
        } else {
            System.out.println("Неизвестное оборудование: " + equipmentName);
        }
    }

    public T getItemByName(String itemName) {
        for (T item : items) {
            if (item instanceof Item && ((Item) item).getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
