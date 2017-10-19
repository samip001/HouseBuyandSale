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
public class Booked implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String postownername;
    private String bookedusername;
    private String houseTypeName;
    private int houseId;
    private Date requestDate;
    private Date houseEndDate;
    private Date bookedDate;
    private float actualPrice;
    private float commisionPrice;
    private float totalCost;
    private String requestStatus;
    private String userStatus;

    public Booked() {
    }

    public Booked(Integer id) {
        this.id = id;
    }

    public Booked(Integer id, String postownername, String bookedusername, String houseTypeName, int houseId, Date requestDate, Date houseEndDate, float actualPrice, float commisionPrice, float totalCost, String requestStatus) {
        this.id = id;
        this.postownername = postownername;
        this.bookedusername = bookedusername;
        this.houseTypeName = houseTypeName;
        this.houseId = houseId;
        this.requestDate = requestDate;
        this.houseEndDate = houseEndDate;
        this.actualPrice = actualPrice;
        this.commisionPrice = commisionPrice;
        this.totalCost = totalCost;
        this.requestStatus = requestStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostownername() {
        return postownername;
    }

    public void setPostownername(String postownername) {
        this.postownername = postownername;
    }

    public String getBookedusername() {
        return bookedusername;
    }

    public void setBookedusername(String bookedusername) {
        this.bookedusername = bookedusername;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getHouseEndDate() {
        return houseEndDate;
    }

    public void setHouseEndDate(Date houseEndDate) {
        this.houseEndDate = houseEndDate;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public float getCommisionPrice() {
        return commisionPrice;
    }

    public void setCommisionPrice(float commisionPrice) {
        this.commisionPrice = commisionPrice;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booked)) {
            return false;
        }
        Booked other = (Booked) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.BookedList[ id=" + id + " ]";
    }
    
}
