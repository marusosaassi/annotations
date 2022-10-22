package org.marusosa.annotations.example.processor;

import org.marusosa.annotations.example.JsonAttribute;
import org.marusosa.annotations.example.processor.exception.JsonSerializerException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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
                    Object value = f.get(object);
                    if(f.getAnnotation(JsonAttribute.class).capitalizer() &&
                    value instanceof String) {
                        String newValue = (String) value;
                        /*newValue = newValue.substring(0,1).toUpperCase() +
                            newValue.substring(1).toLowerCase();*/
                        /*newValue = String.valueOf(newValue.charAt(0)).toUpperCase() +
                            newValue.substring(1).toLowerCase();*/
                        newValue = Arrays.stream(newValue.split(" "))
                            .map(word -> word.substring(0,1 ).toUpperCase()
                                + word.substring(1).toLowerCase())
                            .collect(Collectors.joining(" "));
                        f.set(object, newValue);
                    }
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
