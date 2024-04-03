package org.example;


import java.util.ArrayList;
import java.util.List;

class Hotel {
    private  String name;
    private  int rates;

    public Hotel(String name, int rates) {
        this.name = name;
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", rates=" + rates +
                '}';
    }
}

public class HotelSystem {
    public static void main(String[] args) {
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel1 = new Hotel("Lakewood",110);
        hotelList.add(hotel1);
        Hotel hotel2 = new Hotel("Bridgewood",160);
        hotelList.add(hotel2);
        Hotel hotel3 = new Hotel("Ridgewood",210);
        hotelList.add(hotel3);

        System.out.println("Hotelist are : " + hotelList);
    }
}
