package oncall.controller;

import java.util.List;
import oncall.model.Month;
import oncall.view.InputView;

public class GameController {
    private List<String> weekdayPerson;
    private List<String> weekendPerson;
    private int month;
    private String day;

    public void startGame() {
        List<String> monthAndDay = InputController.makeMonthAndDay();
        month = Integer.parseInt(monthAndDay.get(0));
        day = monthAndDay.get(1);
        weekdayPerson = makeWeekdayPerson();
        weekendPerson = makeWeekendPerson(weekdayPerson);
        List<Integer> holiday = new Month().makeHoliday(month);

    }

    public List<String> makeWeekdayPerson() {
        try {
            List<String> weekdayPerson = InputView.inputWeekdayPerson();

            return weekdayPerson;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeWeekdayPerson();
        }
    }

    public List<String> makeWeekendPerson(List<String> weekdayPersons) {
        try {
            List<String> weekendPerson = InputView.inputWeekendPerson(weekdayPersons);

            return weekendPerson;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            weekdayPerson = makeWeekdayPerson();
            return makeWeekendPerson(weekdayPerson);
        }
    }
}