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
 */public class FollowRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String requestedUser;
    private String status;

    public FollowRequest() {
    }

    public FollowRequest(Integer id) {
        this.id = id;
    }

    public FollowRequest(Integer id, String username, String requestedUser, String status) {
        this.id = id;
        this.username = username;
        this.requestedUser = requestedUser;
        this.status = status;
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

    public String getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(String requestedUser) {
        this.requestedUser = requestedUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof FollowRequest)) {
            return false;
        }
        FollowRequest other = (FollowRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FollowRequest[ id=" + id + " ]";
    }
    
}
