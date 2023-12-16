package oncall.controller;

import java.util.List;
import oncall.view.InputView;

public class InputController {
    public static List<String> makeMonthAndDay() {
        try {
            List<String> monthAndDay = InputView.inputMonthAndDay();

            return monthAndDay;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeMonthAndDay();
        }
    }

}