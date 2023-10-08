package com.devsuperior.userdept.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user") //aqui você fala qual vai ser o nome da tabela
public class User {


    @Id //indica que o id vai ser na tabela uma PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que o id na tabela vai ser AUTO INCREMENT
    private Long id;
    private String name;
    private String email;

    /***
     * Um departamento pode ter vários usuários(n), já o usuário pode ter 1 departamento(1)
     * [user (n) --- (1) department], ou seja, n --- 1 é (muitos para 1) o atributo department
     ***/
    @ManyToOne
    @JoinColumn(name = "department_id") //nome da FOREIGN KEY
    private Department department;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
