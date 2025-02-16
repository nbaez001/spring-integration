package com.empresa.proyecto.sp.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
}
