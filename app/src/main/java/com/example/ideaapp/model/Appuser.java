package com.example.ideaapp.model;

import java.io.Serializable;
import java.util.Arrays;


public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;


	private int userid;

	private String username;

	private String userpw;

	private String useremail;

	private byte[] userpic;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "groupowner", cascade = CascadeType.ALL)
//	private Set<IdeaGroup> ideagroupCollection;

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "appuser", orphanRemoval = true)
//	private Set<GroupMember> groupmemberCollection = new HashSet<GroupMember>();
	
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "author")
//	private Set<Comments> commentCollection = new HashSet<Comments>(); // !!Von Collection zu Set ge�ndert wegen muktipleBag Exception !!
    
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
//    private Collection<Comments> commentsCollection;

	public Appuser() {
	}

	public Appuser(int userid, String username, String userpw, String useremail, byte[] userpic) {
		this.userid = userid;
		this.username = username;
		this.userpw = userpw;
		this.useremail = useremail;
		this.userpic = userpic;
	}

//	public Collection<IdeaGroup> getIdeagroupCollection() {
//		return ideagroupCollection;
//	}
//
//	public Collection<GroupMember> getGroupmemberCollection() {
//		return groupmemberCollection;
//	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public byte[] getUserpic() {
		return userpic;
	}

	public void setUserpic(byte[] userpic) {
		this.userpic = userpic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}


//	public void setIdeagroupCollection(Set<IdeaGroup> ideagroupCollection) {
//		this.ideagroupCollection = ideagroupCollection;
//	}
//
//	// ArrayList in HashSet wegen java.lang.ClassCastException: class
//	// java.util.ArrayList cannot be cast to class java.util.Set
//	// (java.util.ArrayList and java.util.Set ar
//	public void addIdeaGroup(IdeaGroup ideagroup) {
//		if (ideagroupCollection == null)
//			this.ideagroupCollection = new HashSet<IdeaGroup>();
//		ideagroupCollection.add(ideagroup);
//		ideagroup.setGroupowner(this);
//	}
//
//	public void setGroupmemberCollection(Set<GroupMember> groupmemberCollection) {
//		this.groupmemberCollection = groupmemberCollection;
//	}
//
//	public void addGroupMember(GroupMember groupmember) {
//		if (groupmemberCollection == null)
//			this.groupmemberCollection = new HashSet<GroupMember>();
//		groupmemberCollection.add(groupmember);
//		groupmember.setAppuser(this);
//	}

	
//	public Set<Comments> getCommentCollection() {
//		return commentCollection;
//	}
//
//	public void setCommentCollection(Set<Comments> commentCollection) {
//		this.commentCollection = commentCollection;
//	}
	
	/*
	public IdeaGroup getLastIdeaGroup() {
		Iterator<IdeaGroup> iterator = ideagroupCollection.iterator();
        
        if(iterator.hasNext()){
            return iterator.next();
        }else{
            return null;
        }
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userid;
		return result;
	}


	
	@Override
	public String toString() {
		return "UserID= " + userid + " | Username= " + username + " | Userpw= " + userpw + " |  User-Email= "
				+ useremail + " | Userpic= " + Arrays.toString(userpic);
	}

	
}
