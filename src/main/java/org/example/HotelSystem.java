package org.example;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Hotel {
    private String name;private int weekdayRate,weekendRate;
    public Hotel(String name, int weekdayRate, int weekendRate) {
        this.name = name;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
    }
    public int getRate(DayOfWeek dayOfWeek) {
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ? weekendRate : weekdayRate;
    }
    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", weekdayRate=" + weekdayRate +
                ", weekendRate=" + weekendRate +
                '}';
    }
}

public class HotelSystem {
    static List<Hotel> hotelList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static void addHotel() {
        hotelList.add(new Hotel("Lakewood", 110, 90));
        hotelList.add(new Hotel("Bridgewood", 150, 50));
        hotelList.add(new Hotel("Ridgewood", 220, 150));
        System.out.println(hotelList);
    }

    static void cheapestHotel (){
        System.out.println("Enter the starting  date in (yyyy-mm-dd) : ");
        String startDate=sc.next();
        LocalDate dateStart = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);

        System.out.println("Enter the starting  date in (yyyy-mm-dd) : ");
        String endDate=sc.next();
        LocalDate dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        HotelSystem.hotelRateCalculate(dateStart,dateEnd,hotelList);
    }

    static long hotelRateCalculate(LocalDate startDate, LocalDate endDate, List<Hotel> hotelList) {
        Hotel cheapestHotel = hotelList.get(0);
        long lowestPrice = Long.MAX_VALUE;

        for (Hotel hotel : hotelList) {

            long totalCost = 0;

            LocalDate currentDate = startDate;

            while (!currentDate.isAfter(endDate)) {

                totalCost += hotel.getRate(currentDate.getDayOfWeek());
                currentDate = currentDate.plusDays(1);
            }
            if (totalCost < lowestPrice) {
                lowestPrice = totalCost;
                cheapestHotel = hotel;
            }

        }
        System.out.println("Cheapest Hotel : " + cheapestHotel.toString() + " and price is: " + lowestPrice);
        return lowestPrice ;
    }
    public static void main(String[] args) {
        HotelSystem hotelsystem = new HotelSystem();
        System.out.println("Welcome to my hotel system chain");
        int choice;
        do{

            System.out.println(" 0 : exit , 1 : add Hotels , 2 : chepest Hotel , 3 : cheapest Hotel for specific dates");
            choice = sc.nextInt();
            switch (choice){
                case 0 :
                    System.out.println("Exiting the system...");
                    break;
                case 1 :
                    addHotel();
                    break;
                case 2 :
                    cheapestHotel();
                    break;
                case 3 :

                    LocalDate startDate = LocalDate.parse("2020-09-11", DateTimeFormatter.ISO_DATE);
                    LocalDate endDate = LocalDate.parse("2020-09-12", DateTimeFormatter.ISO_DATE);

                    hotelRateCalculate(startDate, endDate, hotelList);
                    break;
            }
        }while (choice != 0);

        System.out.println("Hotels are : " + hotelList);

        System.out.println("Hotelist are : " + hotelList);
    }
}