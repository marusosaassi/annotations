package org.marusosa.annotations.example;

import org.marusosa.annotations.example.models.Product;
import org.marusosa.annotations.example.processor.JsonSerializer;

import java.time.LocalDate;

public class AnnotationExample {
    public static void main(String[] args) {
        Product product = new Product();
        product.setDate(LocalDate.now());
        product.setName("laptop");
        product.setPrice(1000L);

        System.out.println("json = " + JsonSerializer.convertJson(product));


        //Field[] atributes = product.getClass().getDeclaredFields();
        /* String json = Arrays.stream(atributes).filter(
                f -> f.isAnnotationPresent(JsonAttribute.class)
        ).map(f-> {
            f.setAccessible(true);
            String name = f.getAnnotation(JsonAttribute.class).name().equals("")
                ? f.getName()
                : f.getAnnotation(JsonAttribute.class).name();
            try {
                return "\"" + name + "\":\"" + f.get(product) + "\"";
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error while serializing the json " + e.getMessage());
            }
            //if the 'name' in the attribute is empty, then we assign the name of the object
        })
            .reduce("{", (a,b) -> {
                if("{".equals(a)) {
                    return a + b;
                }
                return a + ", " + b;
            }).concat("}"); */

        //System.out.println("json = " + json);
    }
}
 