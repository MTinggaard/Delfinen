package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
private SwimClub swimClub;
private Chairman chairman;
private Treasurer treasurer;
private Trainer trainer;
private Role loggedInUser;


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

    public boolean getPaidInfo(Member member) { return treasurer.getPaidinfo(member);}

    public boolean setMemberHasPayed(Member member){ return treasurer.setMemberPayed(member);}

    public Chairman getChairman() {
        return chairman;
    }

    public Role getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String username, String password) {
        if(username.equals("formand") && password.equals("1234")){
            this.loggedInUser = Role.CHAIRMAN;
        }else if(username.equals("kasserer") && password.equals("1234")){
            this.loggedInUser = Role.TREASURER;
        }else if(username.equals("træner") && password.equals("1234")){
            this.loggedInUser = Role.TRAINER;
        } else this.loggedInUser = Role.DENIEDUSER;
    }

    public RegistrationOutcome registerResult(Member member, SwimResult newResult) {
        return trainer.registerResult(member, newResult);
    }

    public Member findMember(int memberNumber) {
        return swimClub.findMember(memberNumber);
    }
}
