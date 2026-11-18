package domainmodel;

import java.util.ArrayList;

public class Controller {
private SwimClub swimClub;
private Chairman chairman;
private Treasurer treasurer;

    public Controller() {
        this.swimClub = new SwimClub("Svømmeklubben");
        this.chairman = new Chairman(swimClub);
        this.treasurer = new Treasurer(swimClub);
    }

    public ArrayList<Member> getMemberList() {
        return chairman.getMemberList();
    }

    public boolean isJunior(Member member) {
        return chairman.isJunior(member);
    }
}
