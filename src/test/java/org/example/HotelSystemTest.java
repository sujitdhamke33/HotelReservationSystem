package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class HotelSystemTest {

    @Test
    void rateCalculator(){
        LocalDate startDate = LocalDate.of(2024, 3, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 5);

        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood", 110, 90));
        hotelList.add(new Hotel("Bridgewood", 150, 50));
        hotelList.add(new Hotel("Ridgewood", 220, 150));

        long result = HotelSystem.hotelRateCalculate(startDate,endDate,hotelList);
        System.out.println(result);
        Assertions.assertEquals(510, result);

    }
}