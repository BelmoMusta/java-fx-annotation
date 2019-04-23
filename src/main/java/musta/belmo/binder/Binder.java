package musta.belmo.binder;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import musta.belmo.annotation.ComboBoxField;
import musta.belmo.annotation.PaneField;
import musta.belmo.annotation.TextAreaField;
import musta.belmo.annotation.InputTextField;

import java.lang.reflect.Field;
import java.util.Collection;

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
        if (elementClass.isAnnotationPresent(PaneField.class)) {
            pane = new GridPane();
            Field[] declaredFields = elementClass.getDeclaredFields();
            int row = 0;
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                String id = elementClass.getName() + "::" + declaredField.getName();
                Control control = null;
                Label label = null;

                if (declaredField.isAnnotationPresent(InputTextField.class)) {
                    InputTextField textFieldAnnotation = declaredField.getAnnotation(InputTextField.class);
                    TextField textField = new TextField();
                    String labelName = textFieldAnnotation.label();
                    label = new Label(labelName);
                    control = textField;


                } else if (declaredField.isAnnotationPresent(TextAreaField.class)) {
                    TextAreaField textAreaFieldAnnotation = declaredField.getAnnotation(TextAreaField.class);
                    TextArea textArea = new TextArea();
                    label = new Label(textAreaFieldAnnotation.label());
                    control = textArea;
                } else if (declaredField.isAnnotationPresent(ComboBoxField.class)) {
                    ComboBoxField comboBoxFieldAnnotation = declaredField.getAnnotation(ComboBoxField.class);
                    label = new Label(comboBoxFieldAnnotation.label());
                    ComboBox comboBox = new ComboBox();
                    control = comboBox;
                }

                if (label != null) {
                    if ("##!!##".equals(label.getText())) {
                        label.setText(declaredField.getName());
                    }
                    pane.add(label, 0, row);
                }
                if (control != null) {
                    control.setId(id);
                    pane.add(control, 1, row);
                }
                row++;
            }
        } else
            throw new Exception("the object cannot be bound");
        return pane;
    }

    public <T> void bindElementToForm(T element, Pane pane) {
        Field[] declaredFields = element.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

        }
        pane.getChildren();
    }


    private Node getById(Pane node, String id) {
        return node.getChildren().stream()
                .filter(child -> child.getId().equals(id))
                .findFirst().orElse(null);
    }
}
