package com.example.javahealthrisks.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_RISK_CUSTOMER")
public class RiskCustomerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Integer diseaseScore;

    public RiskCustomerModel() {
    }

    public RiskCustomerModel(Long id, Long customerId, Integer diseaseScore) {
        this.id = id;
        this.customerId = customerId;
        this.diseaseScore = diseaseScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getDiseaseScore() {
        return diseaseScore;
    }

    public void setDiseaseScore(Integer diseaseScore) {
        this.diseaseScore = diseaseScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
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
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (diseaseScore == null) {
            if (other.diseaseScore != null)
                return false;
        } else if (!diseaseScore.equals(other.diseaseScore))
            return false;
        return true;
    }

}
