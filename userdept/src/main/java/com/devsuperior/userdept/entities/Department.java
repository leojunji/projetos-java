package com.devsuperior.userdept.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_department") //aqui você fala qual vai ser o nome da tabela
public class Department {

    @Id //indica que o id vai ser na tabela uma primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que o id na tabela vai ser AUTO INCREMENT
    private Long id;
    private String name;

    public Department() {
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
}
