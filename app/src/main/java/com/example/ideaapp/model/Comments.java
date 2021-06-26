package com.example.ideaapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    private int commentid;

    private String commentcontent;

    private LocalDate commenttime;

    private Appuser author;

    private IdeaCategory ideacategory;

    public Comments(String commentcontent, Appuser author, IdeaCategory ideacategory) {
    	this.commentcontent = commentcontent;
    	this.author = author;
    	this.commenttime = LocalDate.now();
    	this.ideacategory = ideacategory;
    }
    
    public Comments() {
    	
    }

    public Comments(String commentcontent) {
        this.commentcontent = commentcontent;
        this.commenttime = LocalDate.now();
    }
    
    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public LocalDate getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(LocalDate commenttime) {
        this.commenttime = commenttime;
    }

    public Appuser getAuthor() {
        return author;
    }

    public void setAuthor(Appuser author) {
        this.author = author;
    }

    public IdeaCategory getCategory() {
        return ideacategory;
    }

    public void setCategory(IdeaCategory ideacategory) {
        this.ideacategory = ideacategory;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentid;
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
		Comments other = (Comments) obj;
		if (commentid != other.commentid)
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "\ncommentid: " + commentid + "\nauthor: " + author.getUsername() + "content: " + commentcontent + "commenttime: " + commenttime+ "\n";
    }

	public void copyData(Comments comment) {
		this.commentid = comment.commentid;
		this.commentcontent = comment.commentcontent;
		this.commenttime =comment.commenttime;
		this.author = comment.author;
		this.ideacategory  = comment.ideacategory;
		
	}
    
}
