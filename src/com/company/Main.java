package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Ввежите год даты последнего полива");
        int year = scanner.nextInt();
        System.out.println("Ввежите месяц даты последнего полива (номер от 1 до 12)"); //так удобнее для пользователя
        int month = scanner.nextInt() - 1;
        System.out.println("Ввежите день даты последнего полива");
        int day = scanner.nextInt();

        //создание объектов текущей даты и даты последнего полива
        GregorianCalendar currentDate = new GregorianCalendar();
        GregorianCalendar dateOfLastWatering = new GregorianCalendar(year, month, day);

        //вызов функции определения даты полива в зависимости от времени года
        if (getSeason(dateOfLastWatering) == "Winter") {
            System.out.println(getDateWatering(dateOfLastWatering, currentDate, 30));
        } else if (getSeason(dateOfLastWatering) == "Spring") {
            System.out.println(getDateWatering(dateOfLastWatering, currentDate, 7));
        } else if (getSeason(dateOfLastWatering) == "Summer") {
            System.out.println(getDateWatering(dateOfLastWatering, currentDate, 4));
        } else if (getSeason(dateOfLastWatering) == "Autumn") {
            System.out.println(getDateWatering(dateOfLastWatering, currentDate, 7));
        }
    }



    //функция определения времени года
    public static String getSeason(GregorianCalendar dateOfLastWatering) {
        return switch (dateOfLastWatering.get(Calendar.MONTH)) {
            case (GregorianCalendar.DECEMBER), (GregorianCalendar.JANUARY), (GregorianCalendar.FEBRUARY) -> "Winter";
            case (GregorianCalendar.MARCH), (GregorianCalendar.APRIL), (GregorianCalendar.MAY) -> "Spring";
            case (GregorianCalendar.JUNE), (GregorianCalendar.JULY), (GregorianCalendar.AUGUST) -> "Summer";
            case (GregorianCalendar.SEPTEMBER), (GregorianCalendar.OCTOBER), (GregorianCalendar.NOVEMBER) -> "Autumn";
            default -> null;
        };
    }

    //функция определения разницы между датой последнего полива и текущей датой
    public static int getDifference(GregorianCalendar dateOfLastWatering, GregorianCalendar currentDate) {
        int differenceYears = currentDate.get(Calendar.YEAR) - dateOfLastWatering.get(Calendar.YEAR);
        int differenceDays = 0;
        if (differenceYears == 0) {
            differenceDays = currentDate.get(Calendar.DAY_OF_YEAR) - dateOfLastWatering.get(Calendar.DAY_OF_YEAR);
        } else if (differenceYears >= 1) {
            differenceDays = currentDate.get(Calendar.DAY_OF_YEAR) - dateOfLastWatering.get(Calendar.DAY_OF_YEAR)
                    + 365 * differenceYears;
        }
        return differenceDays;
    }

    //функция определения даты полива
    public static Date getDateWatering(GregorianCalendar dateOfLastWatering, GregorianCalendar currentDate, int wateringPeriod) {
        Random random = new Random();
        if (getDifference(dateOfLastWatering, currentDate) >= wateringPeriod)
            return currentDate.getTime();
        int daysCheck = wateringPeriod - getDifference(dateOfLastWatering, currentDate);
        if (daysCheck >= 2) {
            for (int i = 2; i <= daysCheck - 1; i++) {
                if (random.nextInt(0, 100) < 30) {
                    dateOfLastWatering.add(Calendar.DAY_OF_YEAR, i);
                    break;
                }
            }
        }
        return dateOfLastWatering.getTime();
    }
}
