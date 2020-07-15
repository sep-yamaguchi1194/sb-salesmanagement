package sep.salesmanagement.yt.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ExistsDateValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExistsDate {
    String message() default "{validation.ExistsDate.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        ExistsDate[] value();
    }
}
