package com.company;

import java.util.Random;

public class Vehicles {
    Random random = new Random();
    String name;
    String type;
    static int totalBreakdowns;
    int distanceTraveled;
    int normalSpeed;

    Vehicles() {
        distanceTraveled = 0;
    }

    public void moveForAnHour(boolean raining) {
        distanceTraveled += normalSpeed;
    }

    void setSpeedLimit(int limit) {
    }
}

class Car extends Vehicles {
    // Since cars are so fast there is a 30% chance that they can go only with 70km/h speed.
    //normal speed is 80-110km/h.

    String[] nameList = {"Apex", "Starlight", "Dawn", "Passion", "Warrior",
            "Blitz", "Viper", "Ethereal", "Flow", "Tradition",
            "Bolt", "Roamer", "Surge", "Mystery", "Whirlpool",
            "Reach", "Prospect", "Spotlight", "Surge", "Thriller"};

    Car() {
        type = "Car";
        normalSpeed = random.nextInt(30) + 80;
        name = nameList[random.nextInt(20)] + " " + nameList[random.nextInt(20)];

    }

    void setSpeedLimit(int limit) {
        normalSpeed = limit;
    }
}

class Motorcycle extends Vehicles {
    // speed is 100km/h. If rains, travels with 5-50km/h slower (randomly).
    static int nameNumber = 0;

    Motorcycle() {
        nameNumber++;
        name = "Motorcycle " + nameNumber;
        type = "Motorcycle";
        normalSpeed = 100;
    }

    public void moveForAnHour(boolean raining){
        if (raining){
            normalSpeed = random.nextInt(45) + 50;
            distanceTraveled += normalSpeed;
        } else {
            normalSpeed = 100;
            distanceTraveled += normalSpeed;
        }
    }
}

class Truck extends Vehicles {
    // speed: 100km/h. 5% chance of breaking down for 2 hours.
    int breakdownTurnsLeft;

    Truck() {
        type = "Truck";
        name = Integer.toString(random.nextInt(1000));
        normalSpeed = 100;
        breakdownTurnsLeft = 0;
    }

    public void moveForAnHour(boolean raining) {
        if (breakdownTurnsLeft > 0) {
            breakdownTurnsLeft--;
        } else if (random.nextInt(99) < 4) {
            totalBreakdowns++;
            breakdownTurnsLeft = 2;
        } else {
            distanceTraveled += normalSpeed;
        }
    }
}