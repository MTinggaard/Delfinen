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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Navn må ikke være tomt");
        }

        if (birthdate == null) {
            throw new IllegalArgumentException("Fødselsdato skal udfyldes");
        }

        if (birthdate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fødselsdato må ikke være i fremtiden");
        }

        if (memberType == null) {
            throw new IllegalArgumentException("Medlemstype skal udfyldes");
        }

        this.memberNumber = memberNumber;
        this.name = name;
        this.birthdate = birthdate;
        this.memberType = memberType;
        this.swimDisciplin = swimDisciplin;
        this.memberShipFee = new MemberShipFee(this.getAge(),this.memberType);
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
        return (int) ChronoUnit.YEARS.between(birthday, today);
    }

}
