package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Chairman {
    private SwimClub swimClub;

    public Chairman(SwimClub swimClub) {
        this.swimClub = swimClub;
    }

    public Member createMember(String name, LocalDate birthdate, MemberType memberType, ArrayList<SwimDisciplin> swimDiscipline) {
        Member member = new Member(swimClub.getMemberCount() + 1, name, birthdate, memberType, swimDiscipline);
        swimClub.addMember(member);
        return member;
    }

    public ArrayList<Member> getMemberList() {
        return swimClub.getMembers();
    }

    public boolean isJunior(Member member) {
        return member.getAge() < 18;
    }
}