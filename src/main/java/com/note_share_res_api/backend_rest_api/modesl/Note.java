package com.note_share_res_api.backend_rest_api.modesl;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Note {
	
	@Id
	@GeneratedValue
	private Integer id;

	
	private String content;
    
    @ManyToOne
    private User owner;

	@OneToMany
	private List<User> likes;
     
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    public User getUser() {
		return owner;
	}

	public void setUser(User owner) {
		this.owner = owner;
	}


	public void setLikes(List<User> likes){
		this.likes = likes;
	}

	public List<User> getLikes(){
		return likes;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", content=" + content + "]";
	}

}