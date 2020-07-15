package sep.salesmanagement.yt.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsDateValidator implements ConstraintValidator<IsDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        if(value.isEmpty()) {
            return true;
        } else {
            return isDateValid(value);
        }
    }

    private boolean isDateValid(String value) {
        String regex = "\\d{4}/\\d{2}/\\d{2}";
        return Pattern.matches(regex, value);
    }
}
