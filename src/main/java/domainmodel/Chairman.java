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

    public void getMemberList() {
        if (swimClub.getMemberCount() == 0) {
            System.out.println("Der er ingen registrerede medlemmer");
        } else {
            for (Member member : swimClub.getMembers()) {
                String seniorOrJunior;
                if (member.getAge() < 18) {
                    seniorOrJunior = "Junior";
                } else {
                    seniorOrJunior = "Senior";
                }
                System.out.println(member.getMemberNumber() + ", " + member.getName() + ", "
                        + member.getAge() + " år, " + seniorOrJunior + ", " + member.getMemberType());
            }
        }
    }
}