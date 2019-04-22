package musta.belmo.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import musta.belmo.binder.Binder;
import musta.belmo.pojo.UserForm;

public class FxApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserForm userForm = new UserForm();
        userForm.setAge(30);
        userForm.setDescription("desc");
        userForm.setEmail("@__");
        userForm.setUserName("userName");
        Binder binder = new Binder();
        Pane pane = binder.bind(userForm);
        Scene scene = new Scene(pane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
