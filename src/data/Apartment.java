/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.sun.javafx.beans.IDProperty;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author samip
 */
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer apartmentId;
    private String username;
    private int userTypeId;
    private String searching;
    private int apartmentTypeId;
    private String apartmentName;
    private String location;
    private float price;
    private Date postedDate;
    private Date endDate;
    private String paidType;
    private String apratmentDescription;
    private String status;
    private String visibility;

    public Apartment() {
    }

    public Apartment(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Apartment(Integer apartmentId, String username, int userTypeId, String searching, int apartmentTypeId, String apartmentName, String location, float price, Date postedDate, Date endDate, String paidType, String apratmentDescription, String status, String visibility) {
        this.apartmentId = apartmentId;
        this.username = username;
        this.userTypeId = userTypeId;
        this.searching = searching;
        this.apartmentTypeId = apartmentTypeId;
        this.apartmentName = apartmentName;
        this.location = location;
        this.price = price;
        this.postedDate = postedDate;
        this.endDate = endDate;
        this.paidType = paidType;
        this.apratmentDescription = apratmentDescription;
        this.status = status;
        this.visibility = visibility;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getSearching() {
        return searching;
    }

    public void setSearching(String searching) {
        this.searching = searching;
    }

    public int getApartmentTypeId() {
        return apartmentTypeId;
    }

    public void setApartmentTypeId(int apartmentTypeId) {
        this.apartmentTypeId = apartmentTypeId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public String getApratmentDescription() {
        return apratmentDescription;
    }

    public void setApratmentDescription(String apratmentDescription) {
        this.apratmentDescription = apratmentDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apartmentId != null ? apartmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartment)) {
            return false;
        }
        Apartment other = (Apartment) object;
        if ((this.apartmentId == null && other.apartmentId != null) || (this.apartmentId != null && !this.apartmentId.equals(other.apartmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Apartment[ apartmentId=" + apartmentId + " ]";
    }
    
}
