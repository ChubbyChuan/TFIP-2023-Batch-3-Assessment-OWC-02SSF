package ibf2022.batch2.ssf.frontcontroller.model;

import java.io.Serializable;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User implements Serializable {
    @NotBlank(message = "You must key in a username")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String userName;

    @NotBlank(message = "You must key in a password")
    @Size(min = 2, message = "Password must be at least 2 characters")
    private String password;

    private Boolean Auth = false;
    private Boolean captcha = false;
    
    public Boolean getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Boolean captcha) {
        this.captcha = captcha;
    }

    public Boolean getAuth() {
        return Auth;
    }

    public void setAuth(Boolean auth) {
        Auth = auth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add("username", this.getUserName())
                .add("password", this.getPassword());
    }

    public static User createJson(JsonObject o) {
        User u = new User();
        String username = o.getString("username");
        String password = o.getString("password");
        u.setUserName(username);
        u.setPassword(password);
        return u;
    }
}
