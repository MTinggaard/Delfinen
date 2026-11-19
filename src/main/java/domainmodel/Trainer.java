package domainmodel;

import java.util.ArrayList;

public class Trainer {
    private SwimClub swimClub;

    public Trainer(SwimClub swimClub) {
        this.swimClub = swimClub;
    }


    public RegistrationOutcome registerResult(Member member, TrainingResult newResult) {
        if (member.getMemberType() != MemberType.COMPETITIVE) {
            return RegistrationOutcome.NOT_COMPETITIVE;
        }
        return member.registerResult(newResult);
    }
    public RegistrationOutcome registerResult(Member member, CompetitionResult newResult) {
        if (member.getMemberType() != MemberType.COMPETITIVE) {
            return RegistrationOutcome.NOT_COMPETITIVE;
        }
        return member.registerResult(newResult);
    }

    public ArrayList<Member> getJuniorMembers() {
        ArrayList<Member> juniorMembers = new ArrayList<>();

        for (Member member : swimClub.getMembers()) {
            if (member.getMemberType() == MemberType.COMPETITIVE && member.getAge() < 18) {
                juniorMembers.add(member);
            }
        }
        return juniorMembers;
    }
    public ArrayList<Member> getSeniorMembers() {
        ArrayList<Member> seniorMembers = new ArrayList<>();

        for (Member member : swimClub.getMembers()) {
            if (member.getMemberType() == MemberType.COMPETITIVE && member.getAge() >= 18) {
                seniorMembers.add(member);
            }
        }
        return seniorMembers;
    }
}
