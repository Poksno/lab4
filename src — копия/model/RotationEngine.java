package model;

import enums.Direction;
import enums.EngineType;
import interfaces.Motorized;

public class RotationEngine implements Motorized {
    private boolean isRunning;
    private Direction direction;
    private double nozzleAngle;

    public RotationEngine(EngineType rotationEngine) {
        this.isRunning = false;
        this.direction = Direction.NORTH;
        this.nozzleAngle = 0.0;
    }

    @Override
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Поворотный двигатель запущен.");
        } else {
            System.out.println("Поворотный двигатель уже работает.");
        }
    }

    @Override
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Поворотный двигатель остановлен.");
        } else {
            System.out.println("Поворотный двигатель уже остановлен.");
        }
    }

    public void rotateNozzle(Direction newDirection, int angle) {
        if (isRunning) {
            this.direction = newDirection;
            this.nozzleAngle = angle;
            System.out.println("Сопло повернуто " + newDirection + " на " + angle + " градусов.");
        } else {
            System.out.println("Нельзя выполнить поворот, двигатель не активен.");
        }
    }
}
