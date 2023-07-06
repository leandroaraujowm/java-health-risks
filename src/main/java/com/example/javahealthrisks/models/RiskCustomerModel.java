package com.example.javahealthrisks.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_RISK_CUSTOMER")
public class RiskCustomerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customer;
    private Double diseaseScore;

    public RiskCustomerModel() {
    }

    public RiskCustomerModel(Long id, CustomerModel customer, Double diseaseScore) {
        this.id = id;
        this.customer = customer;
        this.diseaseScore = diseaseScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public Double getDiseaseScore() {
        return diseaseScore;
    }

    public void setDiseaseScore(Double diseaseScore) {
        this.diseaseScore = diseaseScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((diseaseScore == null) ? 0 : diseaseScore.hashCode());
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
        RiskCustomerModel other = (RiskCustomerModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (diseaseScore == null) {
            if (other.diseaseScore != null)
                return false;
        } else if (!diseaseScore.equals(other.diseaseScore))
            return false;
        return true;
    }

}
