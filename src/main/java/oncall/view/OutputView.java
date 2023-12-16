package oncall.view;

import java.util.List;
import oncall.model.Day;

public class OutputView {
    private enum Message {
        OUTPUT_GAME_RESULT("%d월 %d일 %s%s%s\n");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public static void printResult(List<Day> result, int month) {
        result.forEach(day1 -> {
            String holi = day1.getHoliday() ? "(휴일)" : " ";
            System.out.printf(Message.OUTPUT_GAME_RESULT.message, month, day1.getDay(), day1.getSevenDay(), holi,
                    day1.getName());
        });
    }
}
