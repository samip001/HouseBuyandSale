/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author samip
 */
public class ApartmentType implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer apartmentTypeId;
    private String apartmentTypeName;

    public ApartmentType() {
    }

    public ApartmentType(Integer apartmentTypeId) {
        this.apartmentTypeId = apartmentTypeId;
    }

    public ApartmentType(Integer apartmentTypeId, String apartmentTypeName) {
        this.apartmentTypeId = apartmentTypeId;
        this.apartmentTypeName = apartmentTypeName;
    }

    public Integer getApartmentTypeId() {
        return apartmentTypeId;
    }

    public void setApartmentTypeId(Integer apartmentTypeId) {
        this.apartmentTypeId = apartmentTypeId;
    }

    public String getApartmentTypeName() {
        return apartmentTypeName;
    }

    public void setApartmentTypeName(String apartmentTypeName) {
        this.apartmentTypeName = apartmentTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apartmentTypeId != null ? apartmentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApartmentType)) {
            return false;
        }
        ApartmentType other = (ApartmentType) object;
        if ((this.apartmentTypeId == null && other.apartmentTypeId != null) || (this.apartmentTypeId != null && !this.apartmentTypeId.equals(other.apartmentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ApartmentType[ apartmentTypeId=" + apartmentTypeId + " ]";
    }
    
}
