package com.example.ideaapp.model;

import java.io.Serializable;

public class GroupMemberPK implements Serializable {
	private static final long serialVersionUID = 1L;

	    private int groupid;

	    private int userid;

	    public GroupMemberPK() {
	    }

	    public GroupMemberPK(int groupid, int userid) {
	        this.groupid = groupid;
	        this.userid = userid;
	    }

	    public int getGroupid() {
	        return groupid;
	    }

	    public void setGroupid(int groupid) {
	        this.groupid = groupid;
	    }

	    public int getUserid() {
	        return userid;
	    }

	    public void setUserid(int userid) {
	        this.userid = userid;
	    }

	    

	    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + groupid;
			result = prime * result + userid;
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
			GroupMemberPK other = (GroupMemberPK) obj;
			if (groupid != other.groupid)
				return false;
			if (userid != other.userid)
				return false;
			return true;
		}

		@Override
	    public String toString() {
	        return "\ngroupid: " + groupid + ", userid: " + userid;
	    }

}