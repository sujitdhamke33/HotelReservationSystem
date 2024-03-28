package org.example;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Hotel {

    private  String name;
    private  int rates;

    public Hotel(String name, int rates) {
        this.name = name;
        this.rates = rates;
    }

    public int getRates() {
        return rates;
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

    static List<Hotel> hotelList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static void addHotel (){
        Scanner sc = new Scanner(System.in);
        Hotel hotel1 = new Hotel("Lakewood",110);
        hotelList.add(hotel1);
        Hotel hotel2 = new Hotel("Bridgewood",160);
        hotelList.add(hotel2);
        Hotel hotel3 = new Hotel("Ridgewood",210);
        hotelList.add(hotel3);
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

    static long hotelRateCalculate (LocalDate dateStart , LocalDate dateEnd, List<Hotel> hotelList){
      Hotel cheapestHotel = hotelList.get(0);
      long lowestPrice = Long.MAX_VALUE;
      long countDay = ChronoUnit.DAYS.between(dateStart,dateEnd);

      for(Hotel hotel : hotelList){
          long totalCost = countDay * hotel.getRates();
          System.out.println("Total cost : " + totalCost);
          if(totalCost < lowestPrice){
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

            System.out.println(" 0 : exit , 1 : add Hotels , 2 : chepest Hotel");
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
            }
        }while (choice != 0);

        System.out.println("Hotels are : " + hotelList);

        System.out.println("Hotelist are : " + hotelList);
    }
}