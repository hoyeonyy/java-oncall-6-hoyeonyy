package oncall.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calendar {
    private final int month;

    public Calendar(int month) {
        this.month = month;
    }

    public List<Day> makeCalendar(String day) {
        List<Day> db = new ArrayList<>();

        boolean holidayChecked = false;
        for (int i = 1; i <= makeMonthCount(month); i++) {
            List<Integer> holiday = new Holiday().makeHoliday(month);
            if (holiday.contains(i)) {
                holidayChecked = true;
            }
            Day newDay = new Day(i, day, holidayChecked);
            holidayChecked = false;
            day = nextDay(day);
            db.add(newDay);
        }
        return db;
    }

    public String nextDay(String day) {
        List<String> daysOfWeek = Arrays.asList("월", "화", "수", "목", "금", "토", "일");
        int index = daysOfWeek.indexOf(day);
        int nextIndex = (index + 1) % daysOfWeek.size();

        return daysOfWeek.get(nextIndex);
    }

    public int makeMonthCount(int month) {
        List<Integer> daysInMonth = Arrays.asList(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        return daysInMonth.get(month);
    }

    public int getMonth() {
        return month;
    }
}
