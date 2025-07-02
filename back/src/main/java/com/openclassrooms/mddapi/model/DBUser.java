package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class DBUser extends AuditTable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;

    @ManyToMany()
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    Set<DBTheme> subscriptions;

    public Set<DBTheme> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(DBTheme theme) {
        if(!CollectionUtils.isEmpty(this.subscriptions)) {
            this.subscriptions.add(theme);
        } else {
            this.subscriptions = new HashSet<>();
            this.subscriptions.add(theme);
        }
    }

    public void setSubscriptions(Set<DBTheme> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
