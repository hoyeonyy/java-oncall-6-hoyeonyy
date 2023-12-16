package oncall.utils.validation;

import java.util.List;
import oncall.utils.ErrorMessage;

public class Validator {
    public void validateMonthAndDay(List<String> input) {
        validateCorrectValue(input);
        validateInteger(input.get(0));
        validateMonth(input.get(0));

        validateDay(input.get(1));
    }

    private void validateCorrectValue(List<String> input) {
        if (input.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    private void validateInteger(String month) {
        if (!month.matches("^[0-9]*$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    private void validateMonth(String month) {
        int realMonth = Integer.parseInt(month);
        if (realMonth < 1 || realMonth > 12) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }

    public void validateDay(String day) {
        if (!day.matches("^(월|화|수|목|금|토|일)$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.showMessage());
        }
    }
}
