package oncall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import oncall.model.Day;
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
        Month realMonth = new Month(month, day);

        List<String> copyWeekday = new ArrayList<>();
        for (String person : weekdayPerson) {
            copyWeekday.add(person);
        }
        List<String> copyWeekend = new ArrayList<>();
        for (String person : weekendPerson) {
            copyWeekend.add(person);
        }
        int weekdayNumber = 0;
        int weekendNumber = 0;
        Stack<String> duplicate = new Stack<>();
        duplicate.push(" ");
        for (Day day1 : realMonth.getMonthDatabase()) {
            if (day1.isWeekday()) {
                if (!duplicate.peek().equals(weekdayPerson.get(weekdayNumber))) {
                    day1.setName(weekdayPerson.get(weekdayNumber));
                    duplicate.push(weekdayPerson.get(weekdayNumber));
                    weekdayNumber++;
                    if (weekdayNumber >= weekdayPerson.size()) {
                        weekdayNumber -= weekdayPerson.size();
                        weekdayPerson = copyWeekday;
                    }
                    continue;
                }
                changeWeekday(weekdayNumber);
                day1.setName(weekdayPerson.get(weekdayNumber));
                duplicate.push(weekdayPerson.get(weekdayNumber));
                weekdayNumber++;
                if (weekdayNumber >= weekdayPerson.size()) {
                    weekdayNumber -= weekdayPerson.size();
                    weekdayPerson = copyWeekday;
                }
            }

            if (day1.isWeekend()) {
                if (!duplicate.peek().equals(weekendPerson.get(weekendNumber))) {
                    day1.setName(weekendPerson.get(weekendNumber));
                    duplicate.push(weekendPerson.get(weekendNumber));
                    weekendNumber++;
                    if (weekendNumber >= weekendPerson.size()) {
                        weekendNumber -= weekendPerson.size();
                        weekendPerson = copyWeekend;
                    }
                    continue;
                }
                changeWeekend(weekendNumber);
                day1.setName(weekendPerson.get(weekendNumber));
                duplicate.push(weekendPerson.get(weekendNumber));
                weekendNumber++;
                if (weekendNumber >= weekendPerson.size()) {
                    weekendNumber -= weekendPerson.size();
                    weekendPerson = copyWeekend;
                }
            }
        }

        for (Day day1 : realMonth.getMonthDatabase()) {
            String holi = " ";
            if (day1.getHoliday()) {
                holi = "(휴일)";
            }
            System.out.println(month + "월 " + day1.getDay() + "일 " + day1.getSevenDay() + holi + day1.getName());
            holi = " ";
        }


    }

    private void changeWeekend(int weekendNumber) {
        String temp = "";
        temp = weekendPerson.get(weekendNumber);
        weekendPerson.set(weekendNumber, weekendPerson.get(weekendNumber + 1));
        weekendPerson.set(weekendNumber + 1, temp);
    }

    private void changeWeekday(int weekdayNumber) {
        String temp = "";
        temp = weekdayPerson.get(weekdayNumber);
        weekdayPerson.set(weekdayNumber, weekdayPerson.get(weekdayNumber + 1));
        weekdayPerson.set(weekdayNumber + 1, temp);
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