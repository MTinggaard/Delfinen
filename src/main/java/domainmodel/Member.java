package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    private int memberNumber;
    private String name;
    private LocalDate birthdate;
    private MemberType memberType;
    private Quota quota;
    private ArrayList<SwimDisciplin> swimDisciplin;
    private ArrayList<SwimResult> results;


}
