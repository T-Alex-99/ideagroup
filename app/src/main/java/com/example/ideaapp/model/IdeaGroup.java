package com.example.ideaapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

//create table ideaGroup (
//	    groupId integer primary key,
//	    groupName varchar2(50) not null,
//	    groupDescr varchar2(200),
//	    groupOwner references appUser(userId) not null,
//	    createDate date
//	    );

public class IdeaGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	private int groupid;

	private String groupname;

	private String groupdescr;

	private String createLocalDate;

	private Appuser groupowner;

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ideagroup", orphanRemoval = true)
//	private Set<GroupMember> groupmemberCollection = new HashSet<GroupMember>(); // !!Von Collection zu Set ge�ndert
																					// wegen muktipleBag Exception !!

	private Collection<IdeaCategory> ideacategoryCollection = new ArrayList<IdeaCategory>(); // !!Von Collection zu Set ge�ndert wegen muktipleBag Exception !!
	
	

	public IdeaGroup() {
	}

	public IdeaGroup(String groupname, String groupdescr) {
		this.groupname = groupname;
		this.groupdescr = groupdescr;
		this.createLocalDate = LocalDate.now().toString();
	}


	public IdeaGroup( int groupid,
					 String groupname, String groupdescr, String createLocalDate, Appuser owner) {
		this.groupid = groupid;
		this.groupname = groupname;
		this.groupdescr = groupdescr;
		this.createLocalDate = createLocalDate;
		this.groupowner = owner;
	}

	// 8.06 Neue Strategie:
//	public void addAppuser(Appuser appuser) {
//		GroupMember groupmember = new GroupMember(this, appuser);
//		groupmemberCollection.add(groupmember);
//		appuser.getGroupmemberCollection().add(groupmember);
//	}
//	
//	public void removeAppuser(Appuser appuser) {
//		for (Iterator<GroupMember> iterator = groupmemberCollection.iterator(); iterator.hasNext();) {
//			GroupMember groupmember = iterator.next();
//
//			if (groupmember.getIdeagroup().equals(this) && groupmember.getAppuser().equals(appuser)) {
//				iterator.remove();
//				groupmember.getAppuser().getGroupmemberCollection().remove(groupmember);
//				groupmember.setAppuser(null);
//				groupmember.setIdeagroup(null);
//			}
//		}
//	}
	
	
	public Appuser getGroupowner() {
		return groupowner;
	}

	public void setIdeagroupCollection(Collection<IdeaCategory> ideacategoryCollection) {
		this.ideacategoryCollection = ideacategoryCollection;
	}
	
	
	
	public void addIdeaCategory(IdeaCategory ideaCategory) {
		if (ideacategoryCollection == null)
			this.ideacategoryCollection = new ArrayList<IdeaCategory>();
		ideacategoryCollection.add(ideaCategory);
		ideaCategory.setIdeagroup(this);
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupdescr() {
		return groupdescr;
	}

	public void setGroupdescr(String groupdescr) {
		this.groupdescr = groupdescr;
	}

	public String getCreateLocalDate() {
		return createLocalDate;
	}

	public void setCreateLocalDate(String createLocalDate) {
		this.createLocalDate = createLocalDate;
	}

//    public Set<IdeaCategory> getIdeacategoryCollection() {
//		return ideacategoryCollection;
//	}
//
//	public void setIdeacategoryCollection(Set<IdeaCategory> ideacategoryCollection) {
//		for (IdeaCategory c : ideacategoryCollection) {
//			c.setIdeagroup(this);
//			this.ideacategoryCollection = ideacategoryCollection;
//		}
//	}

//    public Appuser getGroupowner() {
//        return groupowner;
//    }
//
	public void setGroupowner(Appuser groupowner) {
		this.groupowner = groupowner;
	}

//	public void setGroupmemberCollection(Set<GroupMember> groupmemberCollection) {
//		this.groupmemberCollection = groupmemberCollection;
//	}

//    
//
//    public Collection<GroupMember> getGroupmemberCollection() {
//        return groupmemberCollection;
//    }
//
//    public void setGroupmemberCollection(Set<GroupMember> groupmemberCollection) {
//        this.groupmemberCollection = groupmemberCollection;
//    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupid;
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
		IdeaGroup other = (IdeaGroup) obj;
		if (groupid != other.groupid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\ngroupid: " + groupid + ", groupname: " + groupname + ", groupdescr: " + groupdescr
				+ ", createLocalDate: " + createLocalDate + ", groupowner: " + "ideacategoryCollection: "; // +
																											// ideacategoryCollection;
																											// //+
																											// groupowner
																											// + ",
																											// ideacategoryCollection:
																											// " +
																											// ideacategoryCollection
		// + ", groupmemberCollection: " + groupmemberCollection;
	}

	public void copyData(IdeaGroup ideagroup) {
		this.groupid = ideagroup.groupid;
		this.groupname = ideagroup.groupname;
		this.groupdescr = ideagroup.groupdescr;
		// this.groupowner = ideagroup.groupowner;
		this.createLocalDate = ideagroup.createLocalDate;

	}

}
