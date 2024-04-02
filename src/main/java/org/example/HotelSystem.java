package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Hotel {
    private String name;
    private int weekdayRate, weekendRate, rating,weekdayReward,weekendReward;

    public String getName() {
        return name;
    }

    public int getWeekdayRate() {
        return weekdayRate;
    }

    public int getWeekendRate() {
        return weekendRate;
    }

    public int getWeekdayReward() {
        return weekdayReward;
    }

    public int getWeekendReward() {
        return weekendReward;
    }

    public Hotel(String name, int weekdayRate, int weekendRate, int rating, int weekdayReward, int weekendReward) {
        this.name = name;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
        this.rating = rating;
        this.weekdayReward = weekdayReward;
        this.weekendReward = weekendReward;
    }

    public int getRate(DayOfWeek dayOfWeek) {
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ? weekendRate : weekdayRate;
    }


    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", weekdayRate=" + weekdayRate +
                ", weekendRate=" + weekendRate +
                ", rating=" + rating +
                '}';
    }
}

public class HotelSystem {
    static List<Hotel> hotelList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void addHotel() {
        hotelList.add(new Hotel("Lakewood", 110, 90, 3,80,80));
        hotelList.add(new Hotel("Bridgewood", 150, 50, 4,100,50));
        hotelList.add(new Hotel("Ridgewood", 220, 150, 5,100,40));

        allHotels();
    }

    static void allHotels(){
        for(int i=0;i<hotelList.size();i++)
        {
            Hotel hotel=hotelList.get(i);
            System.out.println(hotel);
        }
    }

    static void cheapestHotel() {
        System.out.println("Enter your starting date in (yyyy-mm-dd): ");
        String startDate = sc.next();
        LocalDate dateStart = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);

        System.out.println("Enter the ending date in (yyyy-mm-dd): ");
        String endDate = sc.next();
        LocalDate dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        hotelRateCalculate(dateStart, dateEnd, hotelList);
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

        System.out.println("Cheapest Hotel: " + cheapestHotel.toString() + " and price is: " + lowestPrice);
        return lowestPrice;
    }

    static void bestRatedCheapestHotel() {
        System.out.println("Enter the starting date in (yyyy-mm-dd): ");
        String startDate = sc.next();
        LocalDate dateStart = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);

        System.out.println("Enter the ending date in (yyyy-mm-dd): ");
        String endDate = sc.next();
        LocalDate dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        bestRatedCheapestHotelRateCalculate(dateStart, dateEnd, hotelList);
    }

    public static long bestRatedCheapestHotelRateCalculate(LocalDate startDate, LocalDate endDate, List<Hotel> hotelList) {
        if (startDate == null || endDate == null || hotelList == null || hotelList.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        LocalDate currentDate = startDate;
        long lowestPrice = Long.MAX_VALUE;
        Hotel bestRatedHotel = null;

        while (!currentDate.isAfter(endDate)) {
            final LocalDate tempDate = currentDate;
            Hotel cheapestHotel = hotelList.stream()
                    .min((hotel1, hotel2) -> {
                        double totalRate1 = tempDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || tempDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                                ? hotel1.getWeekendRate() : hotel1.getWeekdayRate();
                        double totalRate2 = tempDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || tempDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                                ? hotel2.getWeekendRate() : hotel2.getWeekdayRate();
                        return Double.compare(totalRate1, totalRate2);
                    })
                    .orElseThrow(() -> new IllegalArgumentException("Error: No hotels found"));

            long totalCost = cheapestHotel.getRate(currentDate.getDayOfWeek());
            if (totalCost < lowestPrice || (totalCost == lowestPrice && cheapestHotel.getRating() > bestRatedHotel.getRating())) {
                lowestPrice = totalCost;
                bestRatedHotel = cheapestHotel;
            }

            currentDate = currentDate.plusDays(1);
        }

        if (bestRatedHotel == null) {
            throw new IllegalArgumentException("Error: No hotels found");
        }

        System.out.println("Best rated hotel for the given price range: " + bestRatedHotel.toString() + " and price is: " + lowestPrice);
        return lowestPrice;
    }
    public static void rewardCustomer(List<Hotel> hotelList) {
        try (Scanner sc = new Scanner(System.in)) {

            String dateRegex = "\\d{4}-\\d{2}-\\d{2}";

            System.out.println("Enter the starting date in (yyyy-mm-dd): ");
            String startDate = sc.next();
            if (!startDate.matches(dateRegex)) {
                throw new IllegalArgumentException("Error: Invalid date format");
            }
            LocalDate dateStart = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);// string ko object me

            System.out.println("Enter the ending date in (yyyy-mm-dd): ");
            String endDate = sc.next();
            if (!endDate.matches(dateRegex)) {
                throw new IllegalArgumentException("Error: Invalid date format");
            }
            LocalDate dateEnd = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

            long countDays = ChronoUnit.DAYS.between(dateStart, dateEnd);

            Hotel bestRatedHotel = hotelList.stream()
                    .min((hotel1, hotel2) -> {
                        double totalRate1 = countDays * (hotel1.getWeekdayReward() + hotel1.getWeekendReward());
                        double totalRate2 = countDays * (hotel2.getWeekdayReward() + hotel2.getWeekendReward());
                        return Double.compare(totalRate1, totalRate2);
                    })
                    .orElseThrow(() -> new IllegalArgumentException("Error: No hotels found"));

            System.out.println("Best rated hotel for the given date range for reward customers: " + bestRatedHotel.getName() + ", Rating: " + bestRatedHotel.getRating() + " and Total Rates: $" + (countDays * (bestRatedHotel.getWeekdayReward() + bestRatedHotel.getWeekendReward())));
        }
    }
    public static void main(String[] args) {
        HotelSystem hotelsystem = new HotelSystem();
        System.out.println("Welcome to my hotel system chain");
        int choice;
        do {
            System.out.println(" 0 : exit , 1 : add Hotels , 2 : cheapest Hotel , 3 : cheapest Hotel for specific dates, 4 : best rated cheapest hotel for specific dates" +
                    " 5 : best rated chepest hotels, 6 : rewardCustomers");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Exiting the system...");
                    break;
                case 1:
                    addHotel();
                    break;
                case 2:
                    cheapestHotel();
                    break;
                case 3:
                    LocalDate startDate = LocalDate.parse("2020-09-11", DateTimeFormatter.ISO_DATE);
                    LocalDate endDate = LocalDate.parse("2020-09-12", DateTimeFormatter.ISO_DATE);

                    hotelRateCalculate(startDate, endDate, hotelList);
                    break;
                case 4:
                    bestRatedCheapestHotel();
                    break;
                case 5:
                    LocalDate startDates = LocalDate.parse("2020-09-11", DateTimeFormatter.ISO_DATE);
                    LocalDate endDates = LocalDate.parse("2020-09-12", DateTimeFormatter.ISO_DATE);

                    bestRatedCheapestHotelRateCalculate(startDates,endDates,hotelList);
                    break;
                case 6:
                    rewardCustomer(hotelList);
                    break;
            }
        } while (choice != 0);

        System.out.println("Hotels are : " + hotelList);
    }
}
