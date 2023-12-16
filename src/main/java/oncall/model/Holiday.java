package oncall.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Holiday {

    private final Map<Integer, List<Integer>> holidayByMonth;

    public Holiday() {
        Map<Integer, List<Integer>> holiday = new HashMap<>();
        holiday.put(1, Arrays.asList(1));
        holiday.put(3, Arrays.asList(1));
        holiday.put(5, Arrays.asList(5));
        holiday.put(6, Arrays.asList(6));
        holiday.put(8, Arrays.asList(15));
        holiday.put(10, Arrays.asList(3, 9));
        holiday.put(12, Arrays.asList(25));

        this.holidayByMonth = holiday;
    }

    public List<Integer> makeHoliday(int month) {
        return holidayByMonth.getOrDefault(month, Collections.emptyList());
    }
}
