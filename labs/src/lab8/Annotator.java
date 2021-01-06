/*
 * Написать аннотацию с целочисленным параметром. Создать класс, содержащий только приватные методы (3-4шт),
 * аннотировать любые из них. Вызвать из другого класса все аннотированные методы столько раз, сколько указано в
 * параметре аннотации.
 */

package lab8;

import java.lang.reflect.InvocationTargetException;

public class Annotator {
    public static void main(String[] args) {
        try {
            SomePrivateClass spc = new SomePrivateClass();
            Class<?> annotated_class = spc.getClass();

            for (var method : annotated_class.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Repeater.class)) {
                    method.setAccessible(true);

                    for (int i = 0; i < method.getAnnotation(Repeater.class).iteration(); ++i) {
                        method.invoke(spc);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
