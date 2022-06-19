package Usuarios;

import Interface.UserValidationsLogin;

import java.io.Serializable;
import java.util.UUID;

public class Login {
    private String email;
    private UUID code64;
    private String password;

    public Login(String email) {
        this.email = email;
    }

    public Login(String email, UUID code64, String password) {
        this.email = email;
        this.code64 = code64;
        this.password = password;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public UUID getCode64() {return code64;}

    public void setCode64(UUID code64) {this.code64 = code64;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
