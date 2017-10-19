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
public class FollowedUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer followedId;
    private String username;
    private String followingUsername;
    private Date followedDate;
    private String permission;

    public FollowedUser() {
    }

    public FollowedUser(Integer followedId) {
        this.followedId = followedId;
    }

    public FollowedUser(Integer followedId, String username, String followingUsername, Date followedDate, String permission) {
        this.followedId = followedId;
        this.username = username;
        this.followingUsername = followingUsername;
        this.followedDate = followedDate;
        this.permission = permission;
    }

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowingUsername() {
        return followingUsername;
    }

    public void setFollowingUsername(String followingUsername) {
        this.followingUsername = followingUsername;
    }

    public Date getFollowedDate() {
        return followedDate;
    }

    public void setFollowedDate(Date followedDate) {
        this.followedDate = followedDate;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followedId != null ? followedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FollowedUser)) {
            return false;
        }
        FollowedUser other = (FollowedUser) object;
        if ((this.followedId == null && other.followedId != null) || (this.followedId != null && !this.followedId.equals(other.followedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FollowedUser[ followedId=" + followedId + " ]";
    }
    
}
