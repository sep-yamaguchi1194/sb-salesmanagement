package sep.salesmanagement.yt.validation;

import java.text.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsDateValidator implements ConstraintValidator<ExistsDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        if(value.isEmpty()) {
            return true;
        } else {
            return existsDateValid(value);
        }
    }

    private boolean existsDateValid(String value) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
