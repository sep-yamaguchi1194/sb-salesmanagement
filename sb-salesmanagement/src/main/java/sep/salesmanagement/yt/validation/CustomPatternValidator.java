package sep.salesmanagement.yt.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPatternValidator implements ConstraintValidator<CustomPattern, String> {
    private String regex;

    @Override
    public void initialize(CustomPattern customPattern) {
        regex = customPattern.regex();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        if(value.isEmpty()) {
            return true;
        } else {
            return customPatternValid(value);
        }
    }

    private boolean customPatternValid(String value) {
        return Pattern.matches(regex, value);
    }
}
