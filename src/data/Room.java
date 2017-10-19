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

public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer roomId;
    private String username;
    private int userTypeId;
    private String searching;
    private String roomLocation;
    private int roomNo;
    private Date postedDate;
    private Date endDate;
    private float price;
    private String paidType;
    private String roomDescription;
    private String roomStatus;
    private String roomVisibility;

    public Room() {
    }

    public Room(Integer roomId) {
        this.roomId = roomId;
    }

    public Room(Integer roomId, String username, int userTypeId, String searching, String roomLocation, int roomNo, Date postedDate, Date endDate, float price, String paidType, String roomDescription, String roomStatus, String roomVisibility) {
        this.roomId = roomId;
        this.username = username;
        this.userTypeId = userTypeId;
        this.searching = searching;
        this.roomLocation = roomLocation;
        this.roomNo = roomNo;
        this.postedDate = postedDate;
        this.endDate = endDate;
        this.price = price;
        this.paidType = paidType;
        this.roomDescription = roomDescription;
        this.roomStatus = roomStatus;
        this.roomVisibility = roomVisibility;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
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

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomVisibility() {
        return roomVisibility;
    }

    public void setRoomVisibility(String roomVisibility) {
        this.roomVisibility = roomVisibility;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Room[ roomId=" + roomId + " ]";
    }
    
}
