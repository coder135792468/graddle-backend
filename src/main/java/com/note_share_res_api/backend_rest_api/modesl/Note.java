package com.note_share_res_api.backend_rest_api.modesl;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Note {
	
	@Id
	@GeneratedValue
	private Integer id;

	
	private String content;
    
    @ManyToOne
    private User owner;
     
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


 

	@Override
	public String toString() {
		return "Note [id=" + id + ", content=" + content + "]";
	}

}