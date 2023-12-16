package oncall.view;

public class OutputView {
    private enum Message {
        OUTPUT_GAME_START("게임을 시작합니다."),
        OUTPUT_GAME_GOGO("%s가 %d개의 물건을 샀습니다.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public static void printGameStart() {
        System.out.println(Message.OUTPUT_GAME_START.message);

        System.out.printf(Message.OUTPUT_GAME_GOGO.message, "호연", 5);
        //또는
        System.out.println(String.format(Message.OUTPUT_GAME_GOGO.message, "호연", 5));
    }
}
