package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
private SwimClub swimClub;
private Chairman chairman;
private Treasurer treasurer;
private Trainer trainer;

    public Controller() {
        this.swimClub = new SwimClub("Delfinen");
        this.chairman = new Chairman(swimClub);
        this.treasurer = new Treasurer(swimClub);
        this.trainer = new Trainer(swimClub);
    }

    public ArrayList<Member> getMemberList() {
        return chairman.getMemberList();
    }

    public boolean isJunior(Member member) {
        return chairman.isJunior(member);
    }

    public Member createMember(String name, LocalDate birthdate, MemberType memberType, ArrayList<SwimDisciplin> swimDiscipline){
        return chairman.createMember(name, birthdate, memberType, swimDiscipline);
    }

    public double getMemberShipFee(Member member){
        return treasurer.getMembershipFee(member);
    }
}
