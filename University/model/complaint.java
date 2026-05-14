package com.University.model;

import com.University.enums.complaintStatus;

public class complaint {

    private int complaintId;
    private int studentId;
    private String description;
    private String status;
    private String resolution;
    
    
    
	@Override
	public String toString() 
	{
		return "complaint [complaintId=" + complaintId + ", studentId=" + studentId + ", description=" + description
				+ ", status=" + status + ", resolution=" + resolution + "]";
	}
	
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

    // getters & setters

    
}
