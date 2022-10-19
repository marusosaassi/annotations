package org.marusosa.annotations.example;

import java.lang.annotation.*;

@Documented // for documentation, to add it to the api doc
@Target({ElementType.FIELD})
//target: where is the annotation going to be applied
//element type= constructor, field or attribute, type(class)?
@Retention(RetentionPolicy.RUNTIME)
//retention: context where the annotation is going to be executed/run
//class: in compilation time, runtime: in execution time, source: none of them
public @interface JsonAttribute {
    String name() default "";

}
