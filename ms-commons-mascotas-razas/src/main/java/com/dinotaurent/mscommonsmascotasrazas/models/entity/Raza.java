package com.dinotaurent.mscommonsmascotasrazas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "razas")
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(length = 50)
    private String nombre;

    @Transient
    private List<Mascota> mascotas;

    @JsonIgnoreProperties(value = {"raza"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "raza",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RazaMascotas> razaMascotas;

    @Column(name = "Fecha_de_creacion")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Lob
    @JsonIgnore
    private byte[] foto;


    public Raza() {
        this.mascotas = new ArrayList<>();
        this.razaMascotas = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<RazaMascotas> getRazaMascotas() {
        return razaMascotas;
    }

    public void setRazaMascotas(List<RazaMascotas> razaMascotas) {
        this.razaMascotas = razaMascotas;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }



}