/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.boot.rocks.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "app_user_document")
public class AppUserDocument implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "appuserdocumentname")
	private String appuserdocumentname;

	@Column(name = "appuserdocumentdescription")
	private String appuserdocumentdescription;

	@Basic(optional = false)
	@Column(name = "appuserdocumenttype")
	private String appuserdocumenttype;

	@Basic(optional = false)
	@Lob
	@Column(name = "appuserdocumentcontent")
	private byte[] appuserdocumentcontent;

	@JoinColumn(name = "userid", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private AppUser appUser;

	public AppUserDocument() {
	}

	public AppUserDocument(Long id) {
		this.id = id;
	}

	public AppUserDocument(Long id, String appuserdocumentname, String appuserdocumenttype,
			byte[] appuserdocumentcontent) {
		this.id = id;
		this.appuserdocumentname = appuserdocumentname;
		this.appuserdocumenttype = appuserdocumenttype;
		this.appuserdocumentcontent = appuserdocumentcontent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppuserdocumentname() {
		return appuserdocumentname;
	}

	public void setAppuserdocumentname(String appuserdocumentname) {
		this.appuserdocumentname = appuserdocumentname;
	}

	public String getAppuserdocumentdescription() {
		return appuserdocumentdescription;
	}

	public void setAppuserdocumentdescription(String appuserdocumentdescription) {
		this.appuserdocumentdescription = appuserdocumentdescription;
	}

	public String getAppuserdocumenttype() {
		return appuserdocumenttype;
	}

	public void setAppuserdocumenttype(String appuserdocumenttype) {
		this.appuserdocumenttype = appuserdocumenttype;
	}

	public byte[] getAppuserdocumentcontent() {
		return appuserdocumentcontent;
	}

	public void setAppuserdocumentcontent(byte[] appuserdocumentcontent) {
		this.appuserdocumentcontent = appuserdocumentcontent;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
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
		if (!(object instanceof AppUserDocument)) {
			return false;
		}
		AppUserDocument other = (AppUserDocument) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.spring.boot.rocks.model.AppCaseDocument[ id=" + id + " ]";
	}

}
