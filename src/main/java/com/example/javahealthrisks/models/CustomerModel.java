package com.example.javahealthrisks.models;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
public class CustomerModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate birthDay;
    private Character gender;
    private Double diseaseScore;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private Set<DiseaseModel> diseases = new HashSet<>();

    public CustomerModel() {
    }

    public CustomerModel(Long id, String name, LocalDate birthDay, Character gender, Double diseaseScore,
            Instant createdAt, Instant updatedAt, Set<DiseaseModel> diseases) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.diseaseScore = diseaseScore;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.diseases = diseases;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Double getDiseaseScore() {
        return diseaseScore;
    }

    public void setDiseaseScore() {
        int diseaseSum = 0;

        for (DiseaseModel disease : diseases) {
            diseaseSum += disease.getGrade();
        }

        this.diseaseScore = (1 / (1 + Math.pow(Math.E, -(-2.8 + diseaseSum)))) * 100;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<DiseaseModel> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<DiseaseModel> diseases) {
        this.diseases = diseases;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerModel other = (CustomerModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
