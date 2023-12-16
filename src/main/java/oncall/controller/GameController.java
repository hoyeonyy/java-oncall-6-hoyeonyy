package oncall.controller;

import java.util.List;
import oncall.view.InputView;

public class GameController {
    private List<String> weekdayPerson;
    private List<String> weekendPerson;

    public void startGame() {
        List<String> monthAndDay = InputController.makeMonthAndDay();
        weekdayPerson = makeWeekdayPerson();
        weekendPerson = makeWeekendPerson(weekdayPerson);

        for (String person : weekdayPerson) {
            System.out.println(person);
        }
        System.out.println();
        for (String person : weekendPerson) {
            System.out.println(person);
        }
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