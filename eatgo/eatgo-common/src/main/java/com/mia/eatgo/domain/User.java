package com.mia.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotNull
    private Long level;

    private String password;

    public boolean isAdmin() {
        return level >= 3;
    }

    public void updateUser(String name, String email, Long level) {
        this.name = name;
        this.email = email;
        this.level = level;
    }

    public void deactivate() {
        this.level = 0L;
    }

    public boolean isActive() {
        return level > 0;
    }

}
