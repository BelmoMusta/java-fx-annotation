package musta.belmo.binder;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import musta.belmo.annotation.ComboBoxField;
import musta.belmo.annotation.InputTextField;
import musta.belmo.annotation.PaneField;
import musta.belmo.annotation.TextAreaField;

import java.lang.reflect.Field;

/**
 * TODO: Complete the description of this class
 *
 * @author default author
 * @version 0.0.0
 * @since 0.0.0.SNAPSHOT
 */
public class Binder {

    /**
     * TODO: Complete the description of this method
     *
     * @param elementClass {@link T}
     * @return PaneField
     * @throws Exception the raised exception if error.
     */
    public <T> Pane createControls(Class<? extends T> elementClass) throws Exception {
        final GridPane pane;
        AnnotationToControlConverter annotationToControlConverter = new AnnotationToControlConverter();
        if (elementClass.isAnnotationPresent(PaneField.class)) {
            pane = new GridPane();
            Field[] declaredFields = elementClass.getDeclaredFields();
            int row = 0;
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);

                InputTextField textFieldAnnotation = AnnotationWrapper.get(declaredField.getAnnotation(InputTextField.class));
                TextAreaField textAreaFieldAnnotation = AnnotationWrapper.get(declaredField.getAnnotation(TextAreaField.class));
                ComboBoxField comboBoxFieldAnnotation = AnnotationWrapper.get(declaredField.getAnnotation(ComboBoxField.class));
                HBox convert = new HBox();
                convert.setId("NOT_FILLED_YET");
                if (textFieldAnnotation != null) {
                    convert = annotationToControlConverter.convert(textFieldAnnotation);
                } else if (textAreaFieldAnnotation != null) {
                    convert = annotationToControlConverter.convert(textAreaFieldAnnotation);
                } else if (comboBoxFieldAnnotation != null) {
                    convert = annotationToControlConverter.convert(comboBoxFieldAnnotation);
                }

                if (!convert.getId().equals("NOT_FILLED_YET")) {
                    pane.add(convert, 0, row);
                }
                row++;
            }
        } else
            throw new Exception("the object cannot be bound");
        return pane;
    }


}
