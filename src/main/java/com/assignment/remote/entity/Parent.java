package com.assignment.remote.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "parent")
public class Parent extends BaseModel {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Child> children;
}
