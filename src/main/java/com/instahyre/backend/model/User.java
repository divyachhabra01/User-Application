package com.instahyre.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDate createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDate updatedOn;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "passwrd")
    private String password;
    @Column(name = "is_spam")
    private Boolean isSpam;

}
