package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelSystemTest {
    List<Hotel> hotelList = new ArrayList<>();
    @Test
    void HotelListTest() {

        Hotel hotel1 = new Hotel("Lakewood",110);
        hotelList.add(hotel1);
        Hotel hotel2 = new Hotel("Bridgewood",160);
        hotelList.add(hotel1);
        Hotel hotel3 = new Hotel("Ridgewood",210);
        hotelList.add(hotel1);

        System.out.println(hotelList);

        Assertions.assertEquals(3,hotelList.size());
    }

    @Test
    void rateCalculator(){
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood", 110));

        LocalDate startDate = LocalDate.of(2024, 3, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 5);

        long result = HotelSystem.hotelRateCalculate(startDate,endDate,hotelList);
        System.out.println(result);
        Assertions.assertEquals(440, result);

    }
}