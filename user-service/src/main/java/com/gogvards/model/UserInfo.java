package com.gogvards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.micronaut.data.annotation.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(GeneratedValue.Type.UUID)
    private UUID id = UUID.randomUUID();
    private String lastName;
    private String middleName;
    private String firstName;
    private String userRole;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserInfo> child = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private UserInfo parent;
    private String classTitle;
}
