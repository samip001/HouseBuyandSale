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
public class InterestedUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private int houseId;
    private String houseTypeName;
    private Date interestedDate;

    public InterestedUser() {
    }

    public InterestedUser(Integer id) {
        this.id = id;
    }

    public InterestedUser(Integer id, String username, int houseId, String houseTypeName, Date interestedDate) {
        this.id = id;
        this.username = username;
        this.houseId = houseId;
        this.houseTypeName = houseTypeName;
        this.interestedDate = interestedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public Date getInterestedDate() {
        return interestedDate;
    }

    public void setInterestedDate(Date interestedDate) {
        this.interestedDate = interestedDate;
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
        if (!(object instanceof InterestedUser)) {
            return false;
        }
        InterestedUser other = (InterestedUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.InterestedUser[ id=" + id + " ]";
    }
    
}
