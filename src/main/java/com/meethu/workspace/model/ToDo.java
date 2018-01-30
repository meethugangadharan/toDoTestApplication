package com.meethu.workspace.model;

import java.util.Date;

import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.hibernate.validator.constraints.NotBlank;

@Document(collection="todos")
@JsonIgnoreProperties(value = {"createdDate"}, allowGetters = true)
public class ToDo {
	@NotBlank
	@Size(max=100)
	@Indexed(unique=true)
	private String taskName;
	
	@Id
	private String id;
	private Date createdDate;
	private Boolean isCompleted;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	@Override
	public String toString() {
		return String.format("ToDo[Id='%s', TaskName='%s', Completed='%s']",id,taskName,isCompleted);
	}

}
