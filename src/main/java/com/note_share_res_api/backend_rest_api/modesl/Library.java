package com.note_share_res_api.backend_rest_api.modesl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Library {

	public Library() {

	}

	@Id
	@GeneratedValue
	private Integer id;
	private String uid;
	private Integer curNoteId;
	@ManyToOne
	Note note;

	public Library(Integer id, String uid, Integer curNoteId) {
		super();
		this.id = id;
		this.uid = uid;
		this.curNoteId = curNoteId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getNoteId() {
		return curNoteId;
	}

	public void setNoteId(Integer curNoteId) {
		this.curNoteId = curNoteId;
	}

	public void setNote(Note note) {
		this.note = note;
	};

	public Note getNote() {
		return this.note;
	}

}
