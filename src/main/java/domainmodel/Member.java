package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    private int memberNumber;
    private String name;
    private LocalDate birthdate;
    private MemberType memberType;
    private MemberShipFee memberShipFee;
    private ArrayList<SwimDisciplin> swimDisciplin;
    private ArrayList<SwimResult> results;

    public Member(int memberNumber, String name, LocalDate birthdate, MemberType memberType, MemberShipFee memberShipFee, ArrayList<SwimDisciplin> swimDisciplin, ArrayList<SwimResult> results) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.birthdate = birthdate;
        this.memberType = memberType;
        this.memberShipFee = memberShipFee;
        this.swimDisciplin = swimDisciplin;
        this.results = results;
    }
}
