package musta.belmo.binder;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import musta.belmo.annotation.ComboBoxField;
import musta.belmo.annotation.InputTextField;
import musta.belmo.annotation.PaneField;
import musta.belmo.annotation.TextAreaField;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
                    convert = annotationToControlConverter.convert(textFieldAnnotation, declaredField);
                } else if (textAreaFieldAnnotation != null) {
                    convert = annotationToControlConverter.convert(textAreaFieldAnnotation, declaredField);
                } else if (comboBoxFieldAnnotation != null) {
                    convert = annotationToControlConverter.convert(comboBoxFieldAnnotation, declaredField);
                }

                if (!"NOT_FILLED_YET".equals(convert.getId())) {
                    pane.add(convert, 0, row);
                }
                row++;
            }
        } else
            throw new Exception("the object cannot be bound");
        return pane;
    }


    public <T> void mount(T instance, Pane pane) {
        pane.getChildren()
                .stream()
                .filter(child -> child instanceof TextInputControl)
                .forEach(child -> ((TextInputControl) child).setText("lol"));

    }

    private Pane findByID(Pane pane, String id) {
        ObservableList<Node> children = pane.getChildren();
        List<Pane> list = children.stream()
                .filter(child -> child instanceof Pane)
                .map(child -> (Pane) child)
                .collect(Collectors.toList());

        if(pane == null) return null;
        )

    }
}
