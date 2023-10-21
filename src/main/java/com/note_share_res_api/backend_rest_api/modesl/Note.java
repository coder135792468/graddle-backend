package com.note_share_res_api.backend_rest_api.modesl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note {

	@Id
	@GeneratedValue
	private Integer id;
	private String uid;

	private String title;
	private String desc;
	private String qualification;
	private String author;
	private String file;
	private String subject;
	public Integer download;
	@JsonIgnore
	private Boolean approved = false;

	public Note() {
		this.download = 0;
		this.approved = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public void setdownload(Integer download) {
		this.download = download;
	};

	public Integer getDownload() {
		return download;
	}

	public Boolean getApproved() {
		return this.approved;
	};

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", desc=" + desc + "]";
	}

}