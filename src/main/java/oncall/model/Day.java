package oncall.model;

public class Day {
    private enum Week {
        MONDAY("월"),
        TUESDAY("화"),
        WEDNESDAY("수"),
        THURSDAY("목"),
        FRIDAY("금"),
        SATURDAY("토"),
        SUNDAY("일");


        private final String week;

        Week(String week) {
            this.week = week;
        }
    }

    private int day;
    private String sevenDay;
    private boolean holiday;
    private String name;

    public Day(int day, String sevenDay, boolean holiday) {
        this.day = day;
        this.sevenDay = sevenDay;
        this.holiday = holiday;
    }

    public boolean isHoliday() {
        if (sevenDay.equals(Week.SATURDAY.week) || sevenDay.equals(Week.SUNDAY.week) || holiday == true) {
            return true;
        }
        return false;
    }

    public boolean isWeekday() {
        if (holiday == false && (sevenDay.equals(Week.MONDAY.week) || sevenDay.equals(Week.TUESDAY.week)
                || sevenDay.equals(Week.WEDNESDAY.week)
                || sevenDay.equals(Week.THURSDAY.week) || sevenDay.equals(Week.FRIDAY.week))) {
            return true;
        }
        return false;
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


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
