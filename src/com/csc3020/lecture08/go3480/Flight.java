package com.csc3020.lecture08.go3480;

public class Flight {

    private int seats;
    private int passengers;
    private boolean[] isSeatAvailable;
    private int flightNumber;
    private char flightClass;
    private int maxCarryOns, totalCarryOns;
    private int totalCheckedBags;

    {
        seats = 150;
        passengers = 0;
        isSeatAvailable = new boolean[seats];
        for (int i = 0; i < seats; i++) {
            isSeatAvailable[i] = true;
        }
        maxCarryOns = seats * 2;
        totalCarryOns = 0;
        totalCheckedBags = 0;
    }

    public Flight() {}
    public Flight(int flightNumber) {
        this();
        this.flightNumber = flightNumber;
    }

    public Flight(char flightClass) {
        this();
        this.flightClass = flightClass;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public char getFlightClass() {
        return flightClass;
    }
    public void setFlightClass(char flightClass) {
        this.flightClass = flightClass;
    }

    public int getPassengers() {
        return passengers;
    }
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void add1Passenger() {
        if (hasSeating())
            passengers += 1;
        else
            handleTooMany();
    }
    public void add1Passenger(int bags) {
        if (hasSeating()) {
            add1Passenger();
            totalCheckedBags += bags;
        }
    }
    public void add1Passenger(Passenger p) {
        add1Passenger(p.getCheckedBags());
    }
    public void add1Passenger(int bags, int carryOns) {
        if (hasSeating() && hasCarryOnSpace(carryOns)) {
            add1Passenger(bags);
            totalCarryOns += carryOns;
        }
    }
    public void add1Passenger(Passenger p, int carryOns) {
        add1Passenger(p.getCheckedBags(), carryOns);
    }

    private boolean hasSeating() {
        return passengers < seats;
    }
    private boolean hasCarryOnSpace(int carryOns) {
        return totalCarryOns + carryOns <= maxCarryOns;
    }
    private void handleTooMany() {
        System.out.println("Too many!!!");
    }
    public boolean hasRoom(Flight f2) {
        int total = passengers + f2.passengers;
        return total <= seats;
    }
    public Flight createFlightWithBoth(Flight f2) {
        Flight newFlight = new Flight();
        newFlight.seats = seats;
        newFlight.passengers = this.passengers+f2.passengers;
        return newFlight;
    }
    public void print(){
        String str = "Flight [seats = "+this.seats+", passengers = "+this.passengers+"]";
        System.out.println(str);
    }
}