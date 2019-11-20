package com.mia.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;
    private String menu;

    @Transient //db에 안넣음
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;
}
