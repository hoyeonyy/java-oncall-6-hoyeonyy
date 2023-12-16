package oncall.utils.validation;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.Arrays;
import java.util.List;
import oncall.utils.ErrorMessage;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void 정상작동_케이스() {
        Validator validator = new Validator();
        List<String> input = Arrays.asList("3", "월");

        assertThatCode(() -> validator.validateMonthAndDay(input)).doesNotThrowAnyException();
    }

    @Test
    public void 월입력이_잘못들어온_경우() {
        Validator validator = new Validator();
        List<String> input = Arrays.asList("15", "월");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validator.validateMonthAndDay(input))
                .withMessageContaining(ErrorMessage.INVALID_INPUT.showMessage());
    }

    @Test
    public void 이름이_5글자_넘는경우() {
        Validator validator = new Validator();
        List<String> input = Arrays.asList("John", "Jane", "Doe", "AliceWithVeryLongName", "Bob");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> validator.validateWeekdayPerson(input))
                .withMessageContaining(ErrorMessage.INVALID_INPUT.showMessage());
    }

}
