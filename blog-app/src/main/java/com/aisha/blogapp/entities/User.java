package com.aisha.blogapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@EnableTransactionManagement
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@Column(name="user_name", nullable = false)
    private String name;
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();
}
