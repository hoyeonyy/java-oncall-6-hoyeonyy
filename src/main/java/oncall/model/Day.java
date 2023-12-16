package oncall.model;

public class Day {
    private int day;
    private String sevenDay;
    private boolean holiday;
    private String name;

    public Day(int day, String sevenDay, boolean holiday) {
        this.day = day;
        this.sevenDay = sevenDay;
        this.holiday = holiday;
    }

    public int getDay() {
        return day;
    }

    public String getSevenDay() {
        return sevenDay;
    }

    public boolean getHoliday() {
        return holiday;
    }

    public boolean isHoliday() {
        if (sevenDay.equals("토") || sevenDay.equals("일") || holiday == true) {
            return true;
        }
        return false;
    }

    public boolean isWeekday() {
        if (holiday == false && (sevenDay.equals("월") || sevenDay.equals("화") || sevenDay.equals("수")
                || sevenDay.equals("목") || sevenDay.equals("금"))) {
            return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
