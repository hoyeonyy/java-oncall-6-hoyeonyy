package oncall.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    public void 다음요일_올바르게계산() {
        // Given
        Calendar calendar = new Calendar(1);

        // When
        String result = calendar.nextDay("월");

        // Then
        assertThat(result).isEqualTo("화");
    }

    @Test
    public void 월별일수_올바르게계산() {
        // Given
        Calendar calendar = new Calendar(2);

        // When
        int result = calendar.makeMonthCount(2);

        // Then
        assertThat(result).isEqualTo(28); // 2월은 윤년이 아닌 경우 28일
    }
}
