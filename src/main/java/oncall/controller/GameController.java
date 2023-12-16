package oncall.controller;

import static oncall.controller.InputController.makeMonthAndDay;
import static oncall.view.OutputView.printResult;

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
        List<String> monthAndDay = makeMonthAndDay();
        month = Integer.parseInt(monthAndDay.get(0));
        String day = monthAndDay.get(1);

        weekdayWorker = makeWeekdayPerson();
        holidayWorker = makeWeekendPerson(weekdayWorker);
        calendar = new Calendar(month).makeCalendar(day);
    }

    public void startGame() {
        calculateSchedule();
        printResult(calendar, month);
    }

    private void calculateSchedule() {
        List<String> copyWeekday = new ArrayList<>();
        copyWeekday.addAll(weekdayWorker);
        List<String> copyWeekend = new ArrayList<>();
        copyWeekend.addAll(holidayWorker);

        int weekdayNumber = 0;
        int holidayNumber = 0;
        Stack<String> duplicate = new Stack<>();
        duplicate.push(" ");
        for (Day day1 : calendar) {
            if (day1.isWeekday()) {
                if (!duplicate.peek().equals(weekdayWorker.get(weekdayNumber))) {
                    weekdayNumber = makeschedule(day1, weekdayWorker, weekdayNumber, duplicate, copyWeekday);
                    continue;
                }
                changeWeekday(weekdayNumber);
                weekdayNumber = makeschedule(day1, weekdayWorker, weekdayNumber, duplicate, copyWeekday);
            }

            if (day1.isWeekend()) {
                if (!duplicate.peek().equals(holidayWorker.get(holidayNumber))) {
                    holidayNumber = makeschedule(day1, holidayWorker, holidayNumber, duplicate, copyWeekend);
                    continue;
                }
                changeWeekend(holidayNumber);
                holidayNumber = makeschedule(day1, holidayWorker, holidayNumber, duplicate, copyWeekend);
            }
        }
    }

    private int makeschedule(Day day1, List<String> weekdayWorker, int weekdayNumber, Stack<String> duplicate,
                             List<String> copyWeekday) {
        day1.setName(weekdayWorker.get(weekdayNumber));
        duplicate.push(weekdayWorker.get(weekdayNumber));
        weekdayNumber++;
        weekdayNumber = nextNumber(weekdayNumber, weekdayWorker, copyWeekday);
        return weekdayNumber;
    }

    private int nextNumber(int weekdayNumber, List<String> weekdayWorker, List<String> copyWeekday) {
        if (weekdayNumber >= weekdayWorker.size()) {
            weekdayNumber -= weekdayWorker.size();
            weekdayWorker = copyWeekday;
        }
        return weekdayNumber;
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