package musta.belmo.binder;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import musta.belmo.annotation.ComboBox;
import musta.belmo.annotation.TextArea;
import musta.belmo.annotation.TextField;

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
     * @param element {@link T}
     * @return Pane
     * @throws Exception the raised exception if error.
     */
    public <T> Pane bind(T element) throws Exception {
        final GridPane pane;
        Class<?> elementClass = element.getClass();
        if (elementClass.isAnnotationPresent(musta.belmo.annotation.Pane.class)) {
            pane = new GridPane();
            Field[] declaredFields = elementClass.getDeclaredFields();
            int row = 0;
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object value = declaredField.get(element);
                Control control = null;
                Label label = null;
                //  ObservableList<Node> children = pane.getChildren();
                if (declaredField.isAnnotationPresent(TextField.class)) {
                    TextField textFieldAnnotation = declaredField.getAnnotation(TextField.class);
                    String id = textFieldAnnotation.id();
                    javafx.scene.control.TextField textField = new javafx.scene.control.TextField();
                    textField.setId(id);
                    textField.setText(String.valueOf(value));
                    String labelName = textFieldAnnotation.label();
                    label = new Label(labelName);
                    control = textField;


                } else if (declaredField.isAnnotationPresent(TextArea.class)) {
                    TextArea textAreaAnnotation = declaredField.getAnnotation(TextArea.class);
                    String id = textAreaAnnotation.id();
                    javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea();
                    textArea.setId(id);
                    textArea.setText(String.valueOf(value));
                    label = new Label(textAreaAnnotation.label());
                    control = textArea;
                } else if (declaredField.isAnnotationPresent(ComboBox.class)) {
                    ComboBox comboBoxAnnotation = declaredField.getAnnotation(ComboBox.class);
                    String name = comboBoxAnnotation.name();
                    javafx.scene.control.ComboBox comboBox = new javafx.scene.control.ComboBox();
                    comboBox.setId(name);
                    Collection collection = (Collection) value;
                    comboBox.getItems().addAll(collection);
                    control = comboBox;
                }
                if (label != null) {
                    if ("##!!##".equals(label.getText())) {
                        label.setText(declaredField.getName());
                    }
                    pane.add(label, 0, row);
                }
                if (control != null) {
                    pane.add(control, 1, row);
                }

                row++;
            }
        } else
            throw new Exception("the object cannot be bound");
        return pane;
    }
}
