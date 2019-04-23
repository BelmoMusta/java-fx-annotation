package musta.belmo.pojo;

import musta.belmo.annotation.PaneField;
import musta.belmo.annotation.TextAreaField;
import musta.belmo.annotation.InputTextField;

@PaneField
public class UserForm {
    @InputTextField(label = "USerName")
    private String userName;
    @InputTextField
    private String email;
    @InputTextField
    private int age;
    @TextAreaField
    private String description;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
