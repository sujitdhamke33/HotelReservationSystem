package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class HotelSystemTest {
    private List<Hotel> hotelList;
    private HotelSystem hotelSystem;

    @BeforeEach
    public void setUp() {
        hotelList = new ArrayList<>();
        hotelList.add(new Hotel("Lakewood", 110, 90, 3));
        hotelList.add(new Hotel("Bridgewood", 150, 50, 4));
        hotelList.add(new Hotel("Ridgewood", 220, 150, 5));

        hotelSystem = new HotelSystem();
    }
    
    @Test //3
    void rateWeekdayWeekendTest(){
        LocalDate startDate = LocalDate.of(2024, 3, 1);
        LocalDate endDate = LocalDate.of(2024, 3, 5);

        long result = HotelSystem.hotelRateCalculate(startDate,endDate,hotelList);
        System.out.println(result);
        Assertions.assertEquals(510, result);

    }
    @Test //4
    void cheapestHotelRateCalculatorTest(){
        LocalDate startDate = LocalDate.of(2020, 9, 11);
        LocalDate endDate = LocalDate.of(2020, 9, 12);

        long result = HotelSystem.hotelRateCalculate(startDate,endDate,hotelList);
        System.out.println(result);
        Assertions.assertEquals(200, result);

    }
    @Test //5
    void ratingTest(){
        LocalDate startDate = LocalDate.of(2020, 9, 11);
        LocalDate endDate = LocalDate.of(2020, 9, 12);

        List<Hotel> hotelList = new ArrayList<>();

        System.out.println(hotelList);

    }

    @Test //6
    public void testBestRatedCheapestHotelRateCalculate() {
        LocalDate startDate = LocalDate.parse("2020-09-11", DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse("2020-09-12", DateTimeFormatter.ISO_DATE);

        long expected = 200; // Expected cost for the best rated cheapest hotel for the given date range
        long actual = hotelSystem.bestRatedCheapestHotelRateCalculate(startDate, endDate, hotelList);

        Assertions.assertEquals(200,actual);
    }
}