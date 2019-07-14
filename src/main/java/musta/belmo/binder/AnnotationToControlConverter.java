package musta.belmo.binder;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import musta.belmo.annotation.ComboBoxField;
import musta.belmo.annotation.InputTextField;
import musta.belmo.annotation.TextAreaField;

public class AnnotationToControlConverter {

    public HBox convert(InputTextField annotation ){
        TextField textField = new TextField();
        String labelName = annotation.label();
        Label label = new Label(labelName);
        HBox box = new HBox(label, textField);
        box.setId(annotation.name());
        return box;
    }
    public HBox convert(TextAreaField annotation ){
        TextArea textField = new TextArea();
        String labelName = annotation.label();
        Label label = new Label(labelName);
        HBox box = new HBox(label, textField);
        box.setId(annotation.name());
        return box;
    }

    public HBox convert(ComboBoxField annotation) {
        Label label = new Label(annotation.label());
        ComboBox comboBox = new ComboBox();
        HBox box = new HBox(label, comboBox);
        box.setId(annotation.name());
        return box;
    }
}
