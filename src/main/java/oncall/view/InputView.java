package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.utils.Util;

public class InputView {

    private enum Message {
        INPUT_MONEY("구입금액을 입력해 주세요.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public int inputMoney() {
        System.out.println(Message.INPUT_MONEY.message);
        String input = Util.removeSpace(Console.readLine());
        // validate
        return Integer.parseInt(input);
    }
}