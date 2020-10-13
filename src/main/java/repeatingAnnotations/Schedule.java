package repeatingAnnotations;

import java.lang.annotation.Retention;

public @interface Schedule {
    String dayOfMonth() default "first";
    String dayOfWeek() default "Mon";
    int hour();
}
