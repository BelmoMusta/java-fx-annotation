package musta.belmo.binder;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import musta.belmo.annotation.AnnotationConstantes;
import musta.belmo.annotation.ComboBoxField;
import musta.belmo.annotation.InputTextField;
import musta.belmo.annotation.TextAreaField;

import java.lang.reflect.Field;
import java.util.Optional;

public class AnnotationToControlConverter {

    public HBox convert(InputTextField annotation, Field currentField) {
        TextField textField = new TextField();
        String labelName = annotation.label();
        Label label = new Label(labelName);
        HBox box = new HBox(label, textField);
        String name = Optional.of(annotation.name())
                .filter(n -> !AnnotationConstantes.DEFAULT_NAME.equals(n))
                .orElse(currentField.getName());
        String delcaringClassName = currentField.getDeclaringClass().getName();
        textField.setId(delcaringClassName + '#' + name);
        box.setId(delcaringClassName + '#' + name+"!!");

        return box;
    }

    public HBox convert(TextAreaField annotation, Field currentField) {
        TextArea textField = new TextArea();
        String labelName = annotation.label();
        Label label = new Label(labelName);
        HBox box = new HBox(label, textField);
        String name = Optional.of(annotation.name())
                .filter(n -> !AnnotationConstantes.DEFAULT_NAME.equals(n))
                .orElse(currentField.getName());
        String declaringClassName = currentField.getDeclaringClass().getName();
        textField.setId(declaringClassName + '#' + name);
        box.setId(declaringClassName + '#' + name+"!!");
        return box;
    }

    public HBox convert(ComboBoxField annotation, Field currentField) {
        Label label = new Label(annotation.label());
        ComboBox comboBox = new ComboBox();
        HBox box = new HBox(label, comboBox);
        box.setId(annotation.name());
        return box;
    }
}
