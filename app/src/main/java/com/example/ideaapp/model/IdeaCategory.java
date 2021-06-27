package com.example.ideaapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class IdeaCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private int categoryid;

    private String categoryTitle;

    private String created;
    
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "categoryid") // commentIdcategoryid
//    private Collection<Comments> commentsCollection;

    private IdeaGroup ideagroup;

	private Set<Comments> commentCollection = new HashSet<Comments>(); // !!Von Collection zu Set ge�ndert wegen muktipleBag Exception !!

    public IdeaCategory() {
    }

    public IdeaCategory(String categorytitle) {
        this.categoryTitle = categorytitle;
        this.created = LocalDate.now().toString();
    }
    
    public IdeaCategory(String categorytitle, IdeaGroup ideagroup) {
    	this.categoryTitle = categorytitle;
        this.created = LocalDate.now().toString();
        this.ideagroup = ideagroup;
    }
    
    public void addComment(Comments comment, Appuser author) {
		if (commentCollection == null)
			this.commentCollection = new HashSet<Comments>();
		System.out.println("commentCollection.add(comment);");
		commentCollection.add(comment);
		System.out.println("comment.setAuthor(author);");
		comment.setAuthor(author);
		System.out.println("comment.getAuthor().getCommentCollection().add(comment);");
		//comment.getAuthor().getCommentCollection().add(comment);
		System.out.println("comment.setCategory(this);");
		comment.setCategory(this);
	}
    
    public void setIdeagroup(IdeaGroup ideagroup) {
		this.ideagroup = ideagroup;
	}

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategorytitle() {
        return categoryTitle;
    }

    public void setCategorytitle(String categorytitle) {
        this.categoryTitle = categorytitle;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

//    public Collection<Comments> getCommentsCollection() {
//        return commentsCollection;
//    }
//
//    public void setCommentsCollection(Collection<Comments> commentsCollection) {
//        this.commentsCollection = commentsCollection;
//    }
    
    public IdeaGroup getGroup() {
    	return ideagroup;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryid;
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
		IdeaCategory other = (IdeaCategory) obj;
		if (categoryid != other.categoryid)
			return false;
		return true;
	}

    

	@Override
	public String toString() {
		return "IdeaCategory [categoryid=" + categoryid + ", categorytitle=" + categoryTitle + ", created=" + created
				+ ", ideagroup=" + ideagroup + "]";
	}

	public void copyData(IdeaCategory ideacategory) {
		this.categoryid = ideacategory.categoryid;
		this.categoryTitle = ideacategory.categoryTitle;
		this.created = ideacategory.created;
		
	}
    
    
}
