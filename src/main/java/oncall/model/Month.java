package oncall.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Month {
    private List<Day> monthDatabase;

    public Month(int month, String day) {
        List<Day> db = new ArrayList<>();

        boolean holidayChecked = false;
        for (int i = 1; i <= makeMonthCount(month); i++) {
            List<Integer> holiday = makeHoliday(month);
            if (holiday.contains(i)) {
                holidayChecked = true;
            }
            Day newDay = new Day(i, day, holidayChecked);
            holidayChecked = false;
            day = nextDay(day);
            db.add(newDay);
        }
        monthDatabase = db;
    }

    public String nextDay(String day) {
        List<String> daysOfWeek = Arrays.asList("월", "화", "수", "목", "금", "토", "일");
        int index = daysOfWeek.indexOf(day);
        int nextIndex = (index + 1) % daysOfWeek.size();

        return daysOfWeek.get(nextIndex);
    }


    public List<Integer> makeHoliday(int month) {
        List<Integer> holiday = new ArrayList<>();
        if (month == 1) {
            holiday.add(1);
        }
        if (month == 3) {
            holiday.add(1);
        }
        if (month == 5) {
            holiday.add(5);
        }
        if (month == 6) {
            holiday.add(6);
        }
        if (month == 8) {
            holiday.add(15);
        }
        if (month == 10) {
            holiday.add(3);
            holiday.add(9);
        }
        if (month == 12) {
            holiday.add(25);
        }
        return holiday;
    }

    public int makeMonthCount(int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            return 28;
        }
        return 0;
    }

    public List<Day> getMonthDatabase() {
        return monthDatabase;
    }

}
