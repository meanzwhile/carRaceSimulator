package com.company;


import java.util.*;

public class Main {
    static int numberOfRains = 0;
    static boolean isThereASpeedLimit;

    private static boolean isRaining(int chanceOfRaining) {
        Random rand = new Random();
        int chance = rand.nextInt(99);
        if (chance <= (chanceOfRaining - 1)) {
            numberOfRains++;
            return true;
        } else {
            return false;
        }
    }

    // speed limit must be bigger than zero
    // returns the limit as int, if it happens
    private static int isThereSpeedLimit(int chance, int limit) {
        Random rand = new Random();
        if (rand.nextInt(99) <= (chance - 1)) {
            isThereASpeedLimit = true;
            return limit;
        } else {
            return 0;
        }
    }

    private static List<Vehicles> createVehicles(int cars, int motorcycles, int trucks) {
        List<Vehicles> race = new ArrayList<>(cars + motorcycles + trucks);
        for (int i = 0; i < cars; i++) {
            race.add(new Car());
        }
        for (int i = 0; i < motorcycles; i++) {
            race.add(new Motorcycle());
        }
        for (int i = 0; i < trucks; i++) {
            race.add(new Truck());
        }
        return race;
    }

    private static void simulateRace(List<Vehicles> race, int numberOfHours) {
        int speedLimit = isThereSpeedLimit(30, 70);
        if (speedLimit > 0) {
            for (Vehicles car : race) {
                car.setSpeedLimit(speedLimit);
            }
        }
        for (int i = 0; i < numberOfHours; i++) {
            boolean raining = isRaining(30);
            for (Vehicles actualVehicle : race) {
                actualVehicle.moveForAnHour(raining);
            }
        }
    }

    private static void printRaceResults(List<Vehicles> race) {
        Collections.sort(race, new Comparator<Vehicles>() {
            @Override
            public int compare(Vehicles o1, Vehicles o2) {
                return o1.distanceTraveled - o2.distanceTraveled;
            }
        });
        for (Vehicles actual : race) {
            System.out.println(actual.type + " : " + actual.name + " ---- " + actual.distanceTraveled + " km");
        }
    }

    private static void printStatistics(List<Vehicles> race) {
        int carDistance = 0;
        int motorcycleDistance = 0;
        int truckDistance = 0;
        for (Vehicles vehicle : race) {
            if (vehicle.type == "Car") {
                carDistance += vehicle.distanceTraveled;
            } else if (vehicle.type == "Motorcycle") {
                motorcycleDistance += vehicle.distanceTraveled;
            } else {
                truckDistance += vehicle.distanceTraveled;
            }
        }
        System.out.println("\n\nCars traveled a total of " + carDistance + " km. -- speedlimit : " + isThereASpeedLimit);
        System.out.println("Motorcycles traveled a total of " + motorcycleDistance + " km. -- number of rainy hours : " + numberOfRains);
        System.out.println("Trucks traveled a total of " + truckDistance + " km. -- number of breakdowns : " + Vehicles.totalBreakdowns);

    }

    public static void main(String[] args) {
        List<Vehicles> race;
        race = createVehicles(10, 10, 10);
        simulateRace(race, 50);
        printRaceResults(race);
        printStatistics(race);
    }
}
