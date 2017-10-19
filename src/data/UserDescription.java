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
public class UserDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String profileName;
    private String profileDescription;
    private User username;

    public UserDescription() {
    }

    public UserDescription(Integer id) {
        this.id = id;
    }

    public UserDescription(Integer id, String profileName) {
        this.id = id;
        this.profileName = profileName;
    }

    public UserDescription(Integer id,User username ,String profileName,String profileDescription) {
        this.id = id;
        this.username = username;
        this.profileName = profileName;
        this.profileDescription = profileDescription;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
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
        if (!(object instanceof UserDescription)) {
            return false;
        }
        UserDescription other = (UserDescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserDescription[ id=" + id + " ]";
    }
    
}
