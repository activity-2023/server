package fr.cyu.depinfo.activity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")
@PrimaryKeyJoinColumn(name = "user_id")
public class UserAccount extends Person {
    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password_hash")
    private String passwordHash;

    @Column(name = "user_password_salt")
    private String passwordSalt;

    public String getLogin() {
        return login;
    }

    public UserAccount setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserAccount setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public UserAccount setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }
}
