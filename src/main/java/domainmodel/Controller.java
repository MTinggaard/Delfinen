package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private SwimClub swimClub = new SwimClub("Delfinen");
    private Chairman chairman = new Chairman(swimClub);
    private Treasurer treasurer = new Treasurer(swimClub);
    private Trainer trainer = new Trainer(swimClub);

    public Member createMember(String name, LocalDate birthdate, MemberType memberType, ArrayList<SwimDisciplin> swimDiscipline){
        return chairman.createMember(name, birthdate, memberType, swimDiscipline);
    }
}
