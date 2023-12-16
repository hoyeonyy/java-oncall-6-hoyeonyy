package oncall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import oncall.model.Calendar;
import oncall.model.Day;
import oncall.view.InputView;

public class GameController {
    private List<String> weekdayWorker;
    private List<String> holidayWorker;
    private final List<Day> calendar;
    private final int month;

    public GameController() {
        List<String> monthAndDay = InputController.makeMonthAndDay();
        month = Integer.parseInt(monthAndDay.get(0));
        String day = monthAndDay.get(1);

        weekdayWorker = makeWeekdayPerson();
        holidayWorker = makeWeekendPerson(weekdayWorker);
        calendar = new Calendar(month).makeCalendar(day);
    }

    public void startGame() {
        List<String> copyWeekday = new ArrayList<>();
        copyWeekday.addAll(weekdayWorker);
        List<String> copyWeekend = new ArrayList<>();
        copyWeekend.addAll(holidayWorker);

        int weekdayNumber = 0;
        int weekendNumber = 0;
        Stack<String> duplicate = new Stack<>();
        duplicate.push(" ");
        for (Day day1 : calendar) {
            if (day1.isWeekday()) {
                if (!duplicate.peek().equals(weekdayWorker.get(weekdayNumber))) {
                    day1.setName(weekdayWorker.get(weekdayNumber));
                    duplicate.push(weekdayWorker.get(weekdayNumber));
                    weekdayNumber++;
                    if (weekdayNumber >= weekdayWorker.size()) {
                        weekdayNumber -= weekdayWorker.size();
                        weekdayWorker = copyWeekday;
                    }
                    continue;
                }
                changeWeekday(weekdayNumber);
                day1.setName(weekdayWorker.get(weekdayNumber));
                duplicate.push(weekdayWorker.get(weekdayNumber));
                weekdayNumber++;
                if (weekdayNumber >= weekdayWorker.size()) {
                    weekdayNumber -= weekdayWorker.size();
                    weekdayWorker = copyWeekday;
                }
            }

            if (day1.isWeekend()) {
                if (!duplicate.peek().equals(holidayWorker.get(weekendNumber))) {
                    day1.setName(holidayWorker.get(weekendNumber));
                    duplicate.push(holidayWorker.get(weekendNumber));
                    weekendNumber++;
                    if (weekendNumber >= holidayWorker.size()) {
                        weekendNumber -= holidayWorker.size();
                        holidayWorker = copyWeekend;
                    }
                    continue;
                }
                changeWeekend(weekendNumber);
                day1.setName(holidayWorker.get(weekendNumber));
                duplicate.push(holidayWorker.get(weekendNumber));
                weekendNumber++;
                if (weekendNumber >= holidayWorker.size()) {
                    weekendNumber -= holidayWorker.size();
                    holidayWorker = copyWeekend;
                }
            }
        }

        for (Day day1 : calendar) {
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
        temp = holidayWorker.get(weekendNumber);
        holidayWorker.set(weekendNumber, holidayWorker.get(weekendNumber + 1));
        holidayWorker.set(weekendNumber + 1, temp);
    }

    private void changeWeekday(int weekdayNumber) {
        String temp = "";
        temp = weekdayWorker.get(weekdayNumber);
        weekdayWorker.set(weekdayNumber, weekdayWorker.get(weekdayNumber + 1));
        weekdayWorker.set(weekdayNumber + 1, temp);
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
            weekdayWorker = makeWeekdayPerson();
            return makeWeekendPerson(weekdayWorker);
        }
    }
}