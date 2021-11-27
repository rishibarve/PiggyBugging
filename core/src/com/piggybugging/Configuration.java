package com.piggybugging;

public class Configuration {
    private static Configuration single_instance = null;

    private int backgroundSpeed;
    private boolean isDead;

    private Configuration()
    {
        this.backgroundSpeed = 5;
        this.isDead = false;
    }

    public static Configuration getInstance()
    {
        if (single_instance == null)
            single_instance = new Configuration();

        return single_instance;
    }

    public int getBackgroundSpeed() {
        return backgroundSpeed;
    }

    public void setBackgroundSpeed(int backgroundSpeed) {
        this.backgroundSpeed = backgroundSpeed;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        System.out.println("setting dead");
        this.setBackgroundSpeed(0);
        isDead = dead;
    }
}

