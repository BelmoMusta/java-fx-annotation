package musta.belmo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**'
 * TODO: Complete the description of this class
 *
 * @author default author
 * @version 0.0.0
 * @since 0.0.0.SNAPSHOT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputTextField {

    String DEFAULT_LABEL = "##!!##";
    String DEFAULT_NAME = "##";

    String value() default "";

    String name() default AnnotationConstantes.DEFAULT_NAME;

    String label() default AnnotationConstantes.DEFAULT_LABEL;
}
