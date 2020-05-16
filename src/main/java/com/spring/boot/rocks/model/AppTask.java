package com.spring.boot.rocks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "app_task")
public class AppTask implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "{not.empty.taskname}")
	@Column(name = "taskname")
	private String taskname;

	@NotEmpty(message = "{not.empty.taskdescription}")
	@Column(name = "taskdescription")
	private String taskdescription;

	@NotNull(message = "{not.empty.taskunitnumber}")
	@Column(name = "taskunitnumber")
	@Range(min = 1)
	private Integer taskunitnumber;

	@NotEmpty(message = "{not.empty.taskunit}")
	@Column(name = "taskunit")
	private String taskunit;

	@Column(name = "taskdatecreated")
	private Date taskdatecreated;

	@Column(name = "taskcreatedby")
	private String taskcreatedby;

	@Column(name = "taskdatemodified")
	private Date taskdatemodified;

	@Column(name = "taskmodifiedby")
	private String taskmodifiedby;

	@Column(name = "taskcompleted")
	private boolean taskcompleted;

	public AppTask() {
		super();
	}

	public AppTask(Long id, String taskname, String taskdescription, Integer taskunitnumber, String taskunit,
			Date taskdatecreated, String taskcreatedby, Date taskdatemodified, String taskmodifiedby,
			boolean taskcompleted) {
		super();
		this.id = id;
		this.taskname = taskname;
		this.taskdescription = taskdescription;
		this.taskunitnumber = taskunitnumber;
		this.taskunit = taskunit;
		this.taskdatecreated = taskdatecreated;
		this.taskcreatedby = taskcreatedby;
		this.taskdatemodified = taskdatemodified;
		this.taskmodifiedby = taskmodifiedby;
		this.taskcompleted = taskcompleted;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "app_task_user", joinColumns = @JoinColumn(name = "taskid"), inverseJoinColumns = @JoinColumn(name = "userid"))
	@NotEmpty(message = "{not.empty.multi.users}")
	private List<AppUser> users;

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskdescription() {
		return taskdescription;
	}

	public void setTaskdescription(String taskdescription) {
		this.taskdescription = taskdescription;
	}

	public Integer getTaskunitnumber() {
		return taskunitnumber;
	}

	public void setTaskunitnumber(Integer taskunitnumber) {
		this.taskunitnumber = taskunitnumber;
	}

	public String getTaskunit() {
		return taskunit;
	}

	public void setTaskunit(String taskunit) {
		this.taskunit = taskunit;
	}

	public Date getTaskdatecreated() {
		return taskdatecreated;
	}

	public void setTaskdatecreated(Date taskdatecreated) {
		this.taskdatecreated = taskdatecreated;
	}

	public String getTaskcreatedby() {
		return taskcreatedby;
	}

	public void setTaskcreatedby(String taskcreatedby) {
		this.taskcreatedby = taskcreatedby;
	}

	public Date getTaskdatemodified() {
		return taskdatemodified;
	}

	public void setTaskdatemodified(Date taskdatemodified) {
		this.taskdatemodified = taskdatemodified;
	}

	public String getTaskmodifiedby() {
		return taskmodifiedby;
	}

	public void setTaskmodifiedby(String taskmodifiedby) {
		this.taskmodifiedby = taskmodifiedby;
	}

	public boolean isTaskcompleted() {
		return taskcompleted;
	}

	public void setTaskcompleted(boolean taskcompleted) {
		this.taskcompleted = taskcompleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (taskcompleted ? 1231 : 1237);
		result = prime * result + ((taskcreatedby == null) ? 0 : taskcreatedby.hashCode());
		result = prime * result + ((taskdatecreated == null) ? 0 : taskdatecreated.hashCode());
		result = prime * result + ((taskdatemodified == null) ? 0 : taskdatemodified.hashCode());
		result = prime * result + ((taskdescription == null) ? 0 : taskdescription.hashCode());
		result = prime * result + ((taskmodifiedby == null) ? 0 : taskmodifiedby.hashCode());
		result = prime * result + ((taskname == null) ? 0 : taskname.hashCode());
		result = prime * result + ((taskunit == null) ? 0 : taskunit.hashCode());
		result = prime * result + ((taskunitnumber == null) ? 0 : taskunitnumber.hashCode());
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
		AppTask other = (AppTask) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (taskcompleted != other.taskcompleted)
			return false;
		if (taskcreatedby == null) {
			if (other.taskcreatedby != null)
				return false;
		} else if (!taskcreatedby.equals(other.taskcreatedby))
			return false;
		if (taskdatecreated == null) {
			if (other.taskdatecreated != null)
				return false;
		} else if (!taskdatecreated.equals(other.taskdatecreated))
			return false;
		if (taskdatemodified == null) {
			if (other.taskdatemodified != null)
				return false;
		} else if (!taskdatemodified.equals(other.taskdatemodified))
			return false;
		if (taskdescription == null) {
			if (other.taskdescription != null)
				return false;
		} else if (!taskdescription.equals(other.taskdescription))
			return false;
		if (taskmodifiedby == null) {
			if (other.taskmodifiedby != null)
				return false;
		} else if (!taskmodifiedby.equals(other.taskmodifiedby))
			return false;
		if (taskname == null) {
			if (other.taskname != null)
				return false;
		} else if (!taskname.equals(other.taskname))
			return false;
		if (taskunit == null) {
			if (other.taskunit != null)
				return false;
		} else if (!taskunit.equals(other.taskunit))
			return false;
		if (taskunitnumber == null) {
			if (other.taskunitnumber != null)
				return false;
		} else if (!taskunitnumber.equals(other.taskunitnumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppTask [id=" + id + ", taskname=" + taskname + ", taskdescription=" + taskdescription
				+ ", taskunitnumber=" + taskunitnumber + ", taskunit=" + taskunit + ", taskdatecreated="
				+ taskdatecreated + ", taskcreatedby=" + taskcreatedby + ", taskdatemodified=" + taskdatemodified
				+ ", taskmodifiedby=" + taskmodifiedby + ", taskcompleted=" + taskcompleted + "]";
	}

}
