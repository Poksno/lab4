package model;

import enums.EngineType;
import interfaces.Motorized;

public class BrakeEngine implements Motorized {
    private EngineType engineType;
    private boolean isRunning;

    public BrakeEngine(EngineType engineType) {
        this.engineType = engineType;
        this.isRunning = false;
    }

    @Override
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Тормозной двигатель активирован.");
        } else {
            System.out.println("Тормозной двигатель уже активирован.");
        }
    }

    @Override
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Тормозной двигатель остановлен.");
        } else {
            System.out.println("Тормозной двигатель уже остановлен.");
        }
    }

}
