package org.marusosa.annotations.example.processor;

import org.marusosa.annotations.example.JsonAttribute;
import org.marusosa.annotations.example.processor.exception.JsonSerializerException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class JsonSerializer {
    public static String convertJson(Object object) {

        if(Objects.isNull(object)) {
            throw new JsonSerializerException("Object cannot be null");
        }

        Field[] atributes = object.getClass().getDeclaredFields();

        return Arrays.stream(atributes).filter(
                f -> f.isAnnotationPresent(JsonAttribute.class)
            ).map(f-> {
                f.setAccessible(true);
                String name = f.getAnnotation(JsonAttribute.class).name().equals("")
                    ? f.getName()
                    : f.getAnnotation(JsonAttribute.class).name();
                try {
                    return "\"" + name + "\":\"" + f.get(object) + "\"";
                } catch (IllegalAccessException e) {
                    throw new JsonSerializerException("Error while serializing the json " + e.getMessage());
                }
                //if the 'name' in the attribute is empty, then we assign the name of the object
            })
            .reduce("{", (a,b) -> {
                if("{".equals(a)) {
                    return a + b;
                }
                return a + ", " + b;
            }).concat("}");
    }
}
