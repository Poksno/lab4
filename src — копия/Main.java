import exceptions.*;
import model.*;
import enums.*;

public class Main {
    public static void main(String[] args) {
        Traveler traveler1 = new Traveler("Иван", 30, "Россия");
        Traveler traveler2 = new Traveler("Анна", 25, "Украина");
        Traveler traveler3 = new Traveler("John", 35, "США");

        Cabin cabin = new Cabin(new Traveler[]{traveler1, traveler2, traveler3});
        Salon salon = new Salon(3, true, new String[]{"Wi-Fi", "Кофемашина"}, 3);
        Battery battery = new Battery("Основная батарея", 1000);
        MainEngine mainEngine = new MainEngine(EngineType.MAIN_ENGINE);
        BrakeEngine brakeEngine = new BrakeEngine(EngineType.BRAKE_ENGINE);
        RotationEngine rotationEngine = new RotationEngine(EngineType.ROTATION_ENGINE);

        Rocket rocket = new Rocket(mainEngine, brakeEngine, rotationEngine, new Cabin[]{cabin}, salon);
        rocket.addCompartment(new Compartment<FoodItem>("Отсек для продуктов"));
        rocket.addCompartment(new Compartment<ChemicalEquipment>("Химический отсек"));
        rocket.addCompartment(new Compartment<Battery>("Отсек для батарей"));

        rocket.addFoodItem(new FoodItem("Пакет космической еды", 5.0));
        rocket.addFoodItem(new FoodItem("Бутылка с водой", 2.0));
        rocket.activateChemicalEquipment("Скребок CO2");
        rocket.chemicalCompartment.addItem(new ChemicalEquipment("Генератор кислорода"));
        rocket.batteryCompartment.addItem(new Battery("Основная батарея", 10000));
        rocket.batteryCompartment.addItem(new Battery("Резервная батарея", 5000));

        rocket.launch();
        System.out.println("Текущий статус: " + rocket.getStatus());

        rocket.consumeFoodItem("Пакет космической еды", 1.0);
        rocket.activateChemicalEquipment("Скребок CO2");
        rocket.startRotationEngine(Direction.NORTH, 45);
        rocket.leaveEarthOrbit();
        System.out.println("Текущий статус: " + rocket.getStatus());

        rocket.startRotationEngine(Direction.EAST, 30);
        rocket.enterLunarOrbit();
        System.out.println("Текущий статус: " + rocket.getStatus());

        rocket.landOnMoon();
        System.out.println("Текущий статус: " + rocket.getStatus());
        rocket.stopEngine();
        System.out.println("Поворотный двигатель остановлен после успешной посадки на Луну.");

        System.out.println("Информация о путешественниках на борту:");
        for (Traveler t : cabin.getTravelers()) {
            t.displayInfo();
        }

        try {
            cabin.turnOnLights();
            System.out.println("Освещение включено.");

            for (int i = 0; i < 10; i++) {
                battery.discharge(100); // Разрядка на 100 единиц за итерацию
                System.out.println("Текущий заряд батареи: " + battery.getCurrentCharge());

                if (battery.getCurrentCharge() < 200) {
                    throw new FuelLevelException("Низкий уровень заряда батареи: " + battery.getCurrentCharge());
                }
            }

            if (battery.needsMaintenance()) {
                throw new MaintenanceException("Батарея требует обслуживания.");
            }

            cabin.turnOffLights();
            System.out.println("Освещение выключено.");

        } catch (FuelLevelException | MaintenanceException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            cabin.turnOffLights();
            System.out.println("Освещение выключено в блоке finally.");
        }
    }
}