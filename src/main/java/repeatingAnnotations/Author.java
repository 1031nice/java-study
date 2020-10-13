package repeatingAnnotations;

import java.lang.annotation.*;

@Repeatable(Authors.class)
public @interface Author {
    String name();
    String date();
}
