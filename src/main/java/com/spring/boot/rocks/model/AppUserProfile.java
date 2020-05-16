package com.spring.boot.rocks.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "app_user_profile")

public class AppUserProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "designation")
	private String designation;

	@Column(name = "followers")
	private String followers;

	@Column(name = "friends")
	private String friends;

	@Column(name = "fans")
	private String fans;

	@Column(name = "datemodified")
	private String datemodified;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "education")
	private String education;

	@Column(name = "location")
	private String location;

	@Column(name = "notes")
	private String notes;

	@Column(name = "whitepapers")
	@Lob
	private String whitepapers;

	@Column(name = "testimonials")
	@Lob
	private String testimonials;

	@Column(name = "inventions")
	@Lob
	private String inventions;

	@Column(name = "awards")
	@Lob
	private String awards;

	@Column(name = "profilepic")
	@Lob
	private Byte[] profilepic;

	@Enumerated(value = EnumType.STRING)
	private AppUserProfileSkills skills;

	public AppUserProfile() {
	}

	public AppUserProfile(Long id, String designation, String followers, String friends, String fans, String datemodified,
			String nickname, String education, String location, String notes, String whitepapers, String testimonials,
			String inventions, String awards, Byte[] profilepic, AppUserProfileSkills skills) {
		super();
		this.id = id;
		this.designation = designation;
		this.followers = followers;
		this.friends = friends;
		this.fans = fans;
		this.datemodified = datemodified;
		this.nickname = nickname;
		this.education = education;
		this.location = location;
		this.notes = notes;
		this.whitepapers = whitepapers;
		this.testimonials = testimonials;
		this.inventions = inventions;
		this.awards = awards;
		this.profilepic = profilepic;
		this.skills = skills;
	}

	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;
	
	

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getFollowers() {
		return followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getFans() {
		return fans;
	}

	public void setFans(String fans) {
		this.fans = fans;
	}

	

	public String getDatemodified() {
		return datemodified;
	}

	public void setDatemodified(String datemodified) {
		this.datemodified = datemodified;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWhitepapers() {
		return whitepapers;
	}

	public void setWhitepapers(String whitepapers) {
		this.whitepapers = whitepapers;
	}

	public String getTestimonials() {
		return testimonials;
	}

	public void setTestimonials(String testimonials) {
		this.testimonials = testimonials;
	}

	public String getInventions() {
		return inventions;
	}

	public void setInventions(String inventions) {
		this.inventions = inventions;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public Byte[] getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(Byte[] profilepic) {
		this.profilepic = profilepic;
	}

	public AppUserProfileSkills getSkills() {
		return skills;
	}

	public void setSkills(AppUserProfileSkills skills) {
		this.skills = skills;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awards == null) ? 0 : awards.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((datemodified == null) ? 0 : datemodified.hashCode());
		result = prime * result + ((fans == null) ? 0 : fans.hashCode());
		result = prime * result + ((followers == null) ? 0 : followers.hashCode());
		result = prime * result + ((friends == null) ? 0 : friends.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inventions == null) ? 0 : inventions.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + Arrays.hashCode(profilepic);
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		result = prime * result + ((testimonials == null) ? 0 : testimonials.hashCode());
		result = prime * result + ((whitepapers == null) ? 0 : whitepapers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUserProfile other = (AppUserProfile) obj;
		if (awards == null) {
			if (other.awards != null)
				return false;
		} else if (!awards.equals(other.awards))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (education == null) {
			if (other.education != null)
				return false;
		} else if (!education.equals(other.education))
			return false;
		if (datemodified == null) {
			if (other.datemodified != null)
				return false;
		} else if (!datemodified.equals(other.datemodified))
			return false;
		if (fans == null) {
			if (other.fans != null)
				return false;
		} else if (!fans.equals(other.fans))
			return false;
		if (followers == null) {
			if (other.followers != null)
				return false;
		} else if (!followers.equals(other.followers))
			return false;
		if (friends == null) {
			if (other.friends != null)
				return false;
		} else if (!friends.equals(other.friends))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inventions == null) {
			if (other.inventions != null)
				return false;
		} else if (!inventions.equals(other.inventions))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (!Arrays.equals(profilepic, other.profilepic))
			return false;
		if (skills != other.skills)
			return false;
		if (testimonials == null) {
			if (other.testimonials != null)
				return false;
		} else if (!testimonials.equals(other.testimonials))
			return false;
		if (whitepapers == null) {
			if (other.whitepapers != null)
				return false;
		} else if (!whitepapers.equals(other.whitepapers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppUserProfile [id=" + id + ", designation=" + designation + ", followers=" + followers + ", friends="
				+ friends + ", fans=" + fans + ", datemodified=" + datemodified + ", nickname=" + nickname + ", education="
				+ education + ", location=" + location + ", notes=" + notes + ", whitepapers=" + whitepapers
				+ ", testimonials=" + testimonials + ", inventions=" + inventions + ", awards=" + awards
				+ ", profilepic=" + Arrays.toString(profilepic) + ", skills=" + skills + "]";
	}

}
