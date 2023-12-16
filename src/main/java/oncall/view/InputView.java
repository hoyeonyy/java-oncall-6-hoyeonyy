package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.utils.Util;
import oncall.utils.validation.Validator;

public class InputView {

    public static List<String> inputMonthAndDay() {
        System.out.println(Message.INPUT_MONTH_DAY.message);
        List<String> input = Util.splitByComma(Util.removeSpace(Console.readLine()));
        new Validator().validateMonthAndDay(input);
        return input;
    }

    private enum Message {
        INPUT_MONTH_DAY("비상 근무를 배정할 월과 시작 요일을 입력하세요>");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}