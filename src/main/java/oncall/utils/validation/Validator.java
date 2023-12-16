package oncall.utils.validation;

import static oncall.utils.Constants.INTEGER_RANGE;
import static oncall.utils.Constants.LONGEST_NAME;
import static oncall.utils.Constants.MAXIMUM_PEOPLE;
import static oncall.utils.Constants.MINIMUM_PEOPLE;
import static oncall.utils.Constants.MONTH_END;
import static oncall.utils.Constants.MONTH_START;
import static oncall.utils.Constants.WEEK_RANGE;

import java.util.ArrayList;
import java.util.List;
import oncall.utils.ErrorMessage;

public class Validator {
    public void validateMonthAndDay(List<String> input) {
        validateCorrectValue(input);
        validateInteger(input.get(0));
        validateMonth(input.get(0));

        validateDay(input.get(1));
    }

    public void validateWeekdayPerson(List<String> input) {
        validateCorrectSize(input);
        validateCorrectName(input);
        validateDuplicateName(input);
    }

    public void validateWeekendPerson(List<String> input, List<String> weekdayPeople) {
        validateCorrectSize(input);
        validateCorrectName(input);
        validateDuplicateName(input);
        validateOnceAMonthWork(input, weekdayPeople);
    }

    private void validateOnceAMonthWork(List<String> input, List<String> weekdayPeople) {
        for (String weekendPerson : input) {
            if (!weekdayPeople.contains(weekendPerson)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
            }
        }

        for (String weekdayPerson : weekdayPeople) {
            if (!input.contains(weekdayPerson)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
            }
        }
    }

    private void validateDuplicateName(List<String> input) {
        List<String> duplication = new ArrayList<>();
        for (String name : input) {
            if (duplication.contains(name)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
            }
            duplication.add(name);
        }
    }

    private void validateCorrectName(List<String> input) {
        for (String name : input) {
            if (name.length() > LONGEST_NAME) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
            }
        }
    }

    private void validateCorrectSize(List<String> input) {
        if (input.size() < MINIMUM_PEOPLE || input.size() > MAXIMUM_PEOPLE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    private void validateCorrectValue(List<String> input) {
        if (input.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    private void validateInteger(String month) {
        if (!month.matches(INTEGER_RANGE)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    private void validateMonth(String month) {
        int realMonth = Integer.parseInt(month);
        if (realMonth < MONTH_START || realMonth > MONTH_END) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    public void validateDay(String day) {
        if (!day.matches(WEEK_RANGE)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }
}
