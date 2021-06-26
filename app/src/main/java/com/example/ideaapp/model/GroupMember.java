package com.example.ideaapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//create table groupmember (
//	    groupId references ideaGroup(groupId) not null,
//	    userId references appUser(userId) not null,
//	    entryDate date not null, --Dieses Datum darf nur durch insert gesetzt werden nicht durch update, soll nichr mehr updatebar sein wie Geburtsdatum
//	    exitDate date,
//	    primary key(groupId,userId)); --Performanter Zugriffspfad mit PK statt unique

public class GroupMember implements Serializable{
	private static final long serialVersionUID = 1L;

	    protected GroupMemberPK GroupMemberPK;

	    private LocalDate entrydate;

	    private LocalDate exitdate;

	    private IdeaGroup ideagroup;

	    private Appuser appuser;
	    

	    public GroupMember() {
	    }

	    public GroupMember(IdeaGroup ideagroup, Appuser appuser) {
	        this.appuser = appuser;
	        this.ideagroup = ideagroup;
	        this.entrydate = LocalDate.now();
	        this.exitdate = null;
	        this.GroupMemberPK = new GroupMemberPK(ideagroup.getGroupid(), appuser.getUserid());
	    }

	    public GroupMemberPK getGroupMemberPK() {
	        return GroupMemberPK;
	    }

	    public void setGroupMemberPK(GroupMemberPK GroupMemberPK) {
	        this.GroupMemberPK = GroupMemberPK;
	    }

	    public LocalDate getEntrydate() {
	        return entrydate;
	    }

	    public void setEntrydate(LocalDate entrydate) {
	        this.entrydate = entrydate;
	    }

	    public LocalDate getExitdate() {
	        return exitdate;
	    }

	    public void setExitdate(LocalDate exitdate) {
	        this.exitdate = exitdate;
	    }

	    public Appuser getAppuser() {
	        return appuser;
	    }

	    public void setAppuser(Appuser appuser) {
	        this.appuser = appuser;
	    }

	    public IdeaGroup getIdeagroup() {
	        return ideagroup;
	    }

	    public void setIdeagroup(IdeaGroup ideagroup) {
	        this.ideagroup = ideagroup;
	    }

	    

	    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((GroupMemberPK == null) ? 0 : GroupMemberPK.hashCode());
			result = prime * result + ((appuser == null) ? 0 : appuser.hashCode());
			result = prime * result + ((entrydate == null) ? 0 : entrydate.hashCode());
			result = prime * result + ((exitdate == null) ? 0 : exitdate.hashCode());
			result = prime * result + ((ideagroup == null) ? 0 : ideagroup.hashCode());
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
			GroupMember other = (GroupMember) obj;
			if (GroupMemberPK == null) {
				if (other.GroupMemberPK != null)
					return false;
			} else if (!GroupMemberPK.equals(other.GroupMemberPK))
				return false;
			if (appuser == null) {
				if (other.appuser != null)
					return false;
			} else if (!appuser.equals(other.appuser))
				return false;
			if (entrydate == null) {
				if (other.entrydate != null)
					return false;
			} else if (!entrydate.equals(other.entrydate))
				return false;
			if (exitdate == null) {
				if (other.exitdate != null)
					return false;
			} else if (!exitdate.equals(other.exitdate))
				return false;
			if (ideagroup == null) {
				if (other.ideagroup != null)
					return false;
			} else if (!ideagroup.equals(other.ideagroup))
				return false;
			return true;
		}

		@Override
	    public String toString() {
	        return "\nGroupMemberPK: " + GroupMemberPK;
	    }
	    
    
}
