package domainmodel;

import java.util.ArrayList;

public class SwimClub {
    private String name;
    private ArrayList<Member> members = new ArrayList<>();


    public SwimClub(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public int getMemberCount() {
        return members.size();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member findMember(int memberNumber) {
        for (Member member : members) {
            if (memberNumber == member.getMemberNumber()) {
                return member;
            }
        }
        return null;
    }
    
}
