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
    private ArrayList<CompetitionResult> competitionResults = new ArrayList<>();
    private ArrayList<TrainingResult> trainingResults = new ArrayList<>();

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
        this.memberShipFee = new MemberShipFee(getAge(), memberType);
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

    public ArrayList<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public ArrayList<TrainingResult> getTrainingResults() {
        return trainingResults;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = getBirthdate();
        return (int) ChronoUnit.YEARS.between(birthday, today);
    }

    public TrainingResult getTrainingResultFor(SwimDisciplin disciplin) {
        ArrayList<TrainingResult> included = new ArrayList<>();
        for (TrainingResult result : trainingResults) {
            if (result.getDiscipline().equals(disciplin)) {
                included.add(result);
            }
        }
        if(included.isEmpty()){
            return null;
        }
        TrainingResult bestResult = included.getFirst();
        for (TrainingResult result : included){
            if(bestResult.getTime() > result.getTime()){
                bestResult = result;
            }
        }
        return bestResult;
    }


    public RegistrationOutcome registerResult(TrainingResult newResult) {
        if (!swimDisciplin.contains(newResult.getDiscipline())) {
            return RegistrationOutcome.NOT_ACTIVE;
        }
        TrainingResult oldResult = getTrainingResultFor(newResult.getDiscipline());
        if (oldResult == null) {
            trainingResults.add(newResult);
            return RegistrationOutcome.NEW_BEST;
        }
        if (newResult.getTime() < oldResult.getTime()) {
            trainingResults.remove(oldResult);
            trainingResults.add(newResult);
            return RegistrationOutcome.NEW_BEST;
        }

        return RegistrationOutcome.NOT_BETTER;
    }

    public RegistrationOutcome registerResult(CompetitionResult newResult) {
        if (!swimDisciplin.contains(newResult.getDiscipline())) {
            return RegistrationOutcome.NOT_ACTIVE;
        }
        competitionResults.add(newResult);
        return RegistrationOutcome.SUCCESS;
    }


}
