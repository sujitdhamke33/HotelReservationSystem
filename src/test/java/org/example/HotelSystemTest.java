package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelSystemTest {

    @Test
    void HotelListTest() {
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel1 = new Hotel("Lakewood",110);
        hotelList.add(hotel1);
        Hotel hotel2 = new Hotel("Bridgewood",160);
        hotelList.add(hotel1);
        Hotel hotel3 = new Hotel("Ridgewood",210);
        hotelList.add(hotel1);

        System.out.println(hotelList);

        Assertions.assertEquals(3,hotelList.size());
    }
}