package domainmodel;

import java.util.ArrayList;

public class SwimClub {
    private String name;
    private ArrayList<Member> members;

    public SwimClub(String name){
        this.name = name;
    }

    public void addMember(Member member){
        members.add(member);
    }

    public int getMemberCount(){
        return members.size();
    }

}
