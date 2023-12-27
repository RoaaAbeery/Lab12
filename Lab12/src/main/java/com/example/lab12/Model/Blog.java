package com.example.lab12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title should not be Empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String title;

    @NotEmpty(message = "body should not be Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String body;


    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")   //انشاء الفور كي
    @JsonIgnore
    private User user;

}
