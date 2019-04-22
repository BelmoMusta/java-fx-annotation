package musta.belmo.pojo;

import musta.belmo.annotation.Pane;
import musta.belmo.annotation.TextArea;
import musta.belmo.annotation.TextField;

@Pane
public class UserForm {
    @TextField(label = "USerName")
    private String userName;
    @TextField
    private String email;
    @TextField
    private int age;
    @TextArea
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
