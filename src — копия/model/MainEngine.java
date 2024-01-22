package model;

import enums.EngineType;
import interfaces.Motorized;

public class MainEngine implements Motorized {
    private EngineType engineType;
    private boolean isRunning;

    public MainEngine(EngineType engineType) {
        this.engineType = engineType;
        this.isRunning = false;
    }

    @Override
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Основной двигатель запущен для взлета.");
        } else {
            System.out.println("Основной двигатель уже работает.");
        }
    }

    @Override
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Основной двигатель остановлен.");
        } else {
            System.out.println("Основной двигатель уже остановлен.");
        }
    }

}
