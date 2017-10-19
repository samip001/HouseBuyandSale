/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author samip
 */
public class House implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer houseId;
    private String username;
    private int userTypeId;
    private String searching;
    private String houseNo;
    private String location;
    private String houseFor;
    private Date postedDate;
    private Date endDate;
    private float price;
    private String paidType;
    private String houseDescription;
    private String status;
    private String houseVisibility;

    public House() {
    }

    public House(Integer houseId) {
        this.houseId = houseId;
    }

    public House(Integer houseId, String username, int userTypeId, String searching, String houseNo, String location, String houseFor, Date postedDate, Date endDate, float price, String paidType, String status, String houseVisibility) {
        this.houseId = houseId;
        this.username = username;
        this.userTypeId = userTypeId;
        this.searching = searching;
        this.houseNo = houseNo;
        this.location = location;
        this.houseFor = houseFor;
        this.postedDate = postedDate;
        this.endDate = endDate;
        this.price = price;
        this.paidType = paidType;
        this.status = status;
        this.houseVisibility = houseVisibility;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
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

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHouseFor() {
        return houseFor;
    }

    public void setHouseFor(String houseFor) {
        this.houseFor = houseFor;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHouseVisibility() {
        return houseVisibility;
    }

    public void setHouseVisibility(String houseVisibility) {
        this.houseVisibility = houseVisibility;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (houseId != null ? houseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof House)) {
            return false;
        }
        House other = (House) object;
        if ((this.houseId == null && other.houseId != null) || (this.houseId != null && !this.houseId.equals(other.houseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.House[ houseId=" + houseId + " ]";
    }
    
}
