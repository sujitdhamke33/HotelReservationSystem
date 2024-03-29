package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class HotelSystemTest {

    @Test
    void rateCalculator(){
        LocalDate startDate = LocalDate.of(2020, 9, 11);
        LocalDate endDate = LocalDate.of(2020, 9, 12);

        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood", 110, 90,3));
        hotelList.add(new Hotel("Bridgewood", 150, 50,4));
        hotelList.add(new Hotel("Ridgewood", 220, 150,5));

        System.out.println(hotelList);
      //  Assertions.assertEquals(200, result);

    }
}