package model;

import abstracts.Vehicle;
import enums.Direction;
import enums.RocketStatus;
import interfaces.LightingControl;
import interfaces.Motorized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Rocket extends Vehicle implements Motorized, LightingControl {
    private MainEngine mainEngine;
    private BrakeEngine brakeEngine;
    private RotationEngine rotationEngine;
    private Cabin[] cabins;
    private Salon salon;
    private RocketStatus status;
    private boolean lightsOn;

    private List<Compartment<?>> compartments;
    private Compartment<FoodItem> foodCompartment;
    public Compartment<ChemicalEquipment> chemicalCompartment;
    public Compartment<Battery> batteryCompartment;

    public Rocket(MainEngine mainEngine, BrakeEngine brakeEngine, RotationEngine rotationEngine,
                  Cabin[] cabins, Salon salon) {
        this.compartments = new ArrayList<>();
        this.mainEngine = mainEngine;
        this.brakeEngine = brakeEngine;
        this.rotationEngine = rotationEngine;
        this.cabins = cabins;
        this.salon = salon;
        this.status = RocketStatus.ON_GROUND;
        this.lightsOn = false;
        this.foodCompartment = new Compartment<>("Food Compartment");
        this.chemicalCompartment = new Compartment<>("Chemical Compartment");
        this.batteryCompartment = new Compartment<>("Battery Compartment");
    }

    public void addCompartment(Compartment<?> compartment) {
        this.compartments.add(compartment);
    }

    public void launch() {
        if (status == RocketStatus.ON_GROUND) {
            class PreLaunchCheck {
                boolean checkSystems() {
                    System.out.println("Предварительная проверка систем...");
                    return true;
                }
            }

            PreLaunchCheck check = new PreLaunchCheck();
            if (check.checkSystems()) {
                startEngine();
                status = RocketStatus.TAKING_OFF;
                System.out.println("Ракета запущена и готовится к выходу на орбиту.");
                status = RocketStatus.IN_ORBIT;
                System.out.println("Ракета на орбите.");
            } else {
                System.out.println("Предварительная проверка не пройдена. Запуск отменен.");
            }
        } else {
            System.out.println("Запуск невозможен: ракета не на земле.");
        }
    }

    public class MaintenanceException extends RuntimeException {
        public MaintenanceException(String message) {
            super(message);
        }
    }

    public void leaveEarthOrbit() {
        if (status == RocketStatus.IN_ORBIT) {
            System.out.println("Ракета выходит из орбиты Земли и направляется к Луне.");
            status = RocketStatus.TRAVELLING_TO_MOON;
        } else {
            System.out.println("Невозможно выйти из орбиты Земли: ракета не на орбите.");
        }
    }

    public void enterLunarOrbit() {
        if (status == RocketStatus.TRAVELLING_TO_MOON) {
            System.out.println("Ракета приближается к Луне и готовится войти в лунную орбиту.");
            status = RocketStatus.IN_LUNAR_ORBIT;
        } else {
            System.out.println("Вход в лунную орбиту невозможен: ракета не в пути к Луне.");
        }
    }

    public void landOnMoon() {
        if (status == RocketStatus.IN_LUNAR_ORBIT) {
            brakeEngine.startEngine();
            System.out.println("Ракета начинает посадку на Луну.");
            status = RocketStatus.LANDING_ON_MOON;
            System.out.println("Ракета приземлилась на Луну.");
            status = RocketStatus.ON_MOON;
        } else {
            System.out.println("Посадка на Луну невозможна: ракета не на лунной орбите.");
        }
    }

    public void addFoodItem(FoodItem foodItem) {
        foodCompartment.addItem(foodItem);
        System.out.println(foodItem.getName() + " added to the food compartment.");
    }

    interface EquipmentActivator {
        void activate(String name);
    }

    public void activateChemicalEquipment(String equipmentName) {
        EquipmentActivator activator = new EquipmentActivator() {
            @Override
            public void activate(String name) {
                if (checkEquipment(name)) {
                    chemicalCompartment.activateEquipment(name);
                    System.out.println(name + " activated in the chemical compartment.");
                } else {
                    System.out.println("Activation failed: " + name + " is not ready or not available.");
                }
            }

            private boolean checkEquipment(String name) {
                return true;
            }
        };

        activator.activate(equipmentName);
    }

    public static class EngineControl {
        public static void checkEngines() {
            System.out.println("Проверка двигателей...");
        }
    }

    public class LifeSupportSystem {
        void regulateTemperature() {
            System.out.println("Регулирование температуры...");
        }
    }

    class ChemicalCompartment {
        void activateEquipment(String equipmentName) {
        }
    }

    public void addBatteryToCompartment(Battery battery) {
        batteryCompartment.addItem(battery);
        System.out.println(battery.getName() + " Добавлено в батарейный отсек.");
    }

    public void consumeFoodItem(String itemName, double amount) {
        FoodItem foodItem = foodCompartment.getItemByName(itemName);
        if (foodItem != null) {
            foodItem.consume(amount);
        } else {
            System.out.println("Продукт питания с названием " + itemName + " Не найдено в продуктовом отсеке.");
        }
    }

    @Override
    public void startEngine() {
        startMainEngine();
        startRotationEngine(Direction.NORTH, 0);
    }

    @Override
    public void stopEngine() {
        stopMainEngine();
        stopBrakeEngine();
        stopRotationEngine();
        System.out.println("Все двигатели ракеты остановлены.");
    }

    public void startMainEngine() {
        if (status == RocketStatus.ON_GROUND || status == RocketStatus.IN_FLIGHT) {
            mainEngine.startEngine();
            System.out.println("Основной двигатель запущен.");
        } else {
            System.out.println("Основной двигатель не может быть запущен в текущем статусе ракеты.");
        }
    }

    public void stopMainEngine() {
        mainEngine.stopEngine();
    }

    public void startBrakeEngine() {
        if (status == RocketStatus.IN_FLIGHT || status == RocketStatus.LANDING_ON_MOON) {
            brakeEngine.startEngine();
            System.out.println("Тормозной двигатель запущен.");
        } else {
            System.out.println("Тормозной двигатель не может быть запущен в текущем статусе ракеты.");
        }
    }

    public void stopBrakeEngine() {
        brakeEngine.stopEngine();
    }

    public void startRotationEngine(Direction direction, double angle) {
        if (status == RocketStatus.IN_FLIGHT || status == RocketStatus.TRAVELLING_TO_MOON) {
            rotationEngine.startEngine();
            rotationEngine.rotateNozzle(direction, (int) angle);
            System.out.println("Двигатель поворота запущен и сопло повернуто.");
        } else {
            System.out.println("Двигатель поворота не может быть запущен в текущем статусе ракеты.");
        }
    }

    public void stopRotationEngine() {
        rotationEngine.stopEngine();
    }

    @Override
    public void turnOnLights() {
        if (!lightsOn) {
            lightsOn = true;
            System.out.println("Свет в ракете включен.");
        } else {
            System.out.println("Свет в ракете уже включен.");
        }
    }

    @Override
    public void turnOffLights() {
        if (lightsOn) {
            lightsOn = false;
            System.out.println("Свет в ракете выключен.");
        } else {
            System.out.println("Свет в ракете уже выключен.");
        }
    }

    public RocketStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rocket rocket = (Rocket) obj;
        return lightsOn == rocket.lightsOn &&
                mainEngine.equals(rocket.mainEngine) &&
                brakeEngine.equals(rocket.brakeEngine) &&
                rotationEngine.equals(rocket.rotationEngine) &&
                Arrays.equals(cabins, rocket.cabins) &&
                salon.equals(rocket.salon) &&
                status == rocket.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mainEngine, brakeEngine, rotationEngine, salon, status, lightsOn);
        result = 31 * result + Arrays.hashCode(cabins);
        return result;
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "mainEngine=" + mainEngine +
                ", brakeEngine=" + brakeEngine +
                ", rotationEngine=" + rotationEngine +
                ", cabins=" + Arrays.toString(cabins) +
                ", salon=" + salon +
                ", status=" + status +
                ", lightsOn=" + lightsOn +
                '}';
    }
}
