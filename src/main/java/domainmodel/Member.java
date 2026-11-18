package domainmodel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Member {
    private int memberNumber;
    private String name;
    private LocalDate birthdate;
    private MemberType memberType;
    private MemberShipFee memberShipFee;
    private ArrayList<SwimDisciplin> swimDisciplin;
    private ArrayList<SwimResult> results;

    public Member(int memberNumber, String name, LocalDate birthdate, MemberType memberType, ArrayList<SwimDisciplin> swimDisciplin) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.birthdate = birthdate;
        this.memberType = memberType;
        this.memberShipFee = memberShipFee;
        this.swimDisciplin = swimDisciplin;
        this.results = results;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public MemberShipFee getMemberShipFee() {
        return memberShipFee;
    }

    public ArrayList<SwimDisciplin> getSwimDisciplin() {
        return swimDisciplin;
    }

    public ArrayList<SwimResult> getResults() {
        return results;
    }

    public int getAge(){
        LocalDate today = LocalDate.now();
        LocalDate birthday = getBirthdate();
        return (int) ChronoUnit.YEARS.between(today, birthday);
    }
}
