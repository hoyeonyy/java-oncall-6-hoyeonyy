package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class Month {
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
}
