package com.csc3020.lecture07.gz0715;
// Lecture 05: Class//
public class Flight {
    public int passengers;
    public int seats;

    // Constructors
    public Flight() {
        seats = 150;
        passengers = 0;
    }
    public Flight(int seats) {
        // setSeats(seats);
        this.seats = seats;
    }

    // Getter and Setter Functions
    public int getSeat(){
        return  seats;
    }
    public void setSeats(int seats){
        this.seats=seats;
    }
    public int getPassengers() {
        return passengers;
    }
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    // Functions
    public void add1Pass() {
        if (passengers < seats)
            passengers += 1;
        else
            handleTooMany();
    }
    private void handleTooMany() {
        System.out.println("Too many!");
    }
    public boolean hasRoom(Flight f2) {
        int total = passengers + f2.passengers;
        return total <= seats;
    }
    public  Flight createFlightWithBoth(Flight flight2){
        Flight newFlight=new Flight();
        newFlight.seats=seats;
        newFlight.passengers=this.passengers+flight2.passengers;
        return newFlight;
    }
    public void print(){
        String str="Flight[ seats= "+this.seats+",passengers= "+this.passengers+"]";
        System.out.println(str);
    }
}
