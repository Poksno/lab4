package model;

import interfaces.LightingControl;

public class Cabin implements LightingControl {
    private Traveler[] travelers;

    public Cabin(Traveler[] travelers) {
        this.travelers = travelers;
    }

    public Traveler[] getTravelers() {
        return travelers;
    }

    public void setTravelers(Traveler[] travelers) {
        this.travelers = travelers;
    }
    private boolean lightsOn = false;

    @Override
    public void turnOnLights() {
        this.lightsOn = true;
    }

    @Override
    public void turnOffLights() {
        this.lightsOn = false;
    }
}
