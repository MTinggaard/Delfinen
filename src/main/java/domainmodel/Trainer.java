package domainmodel;

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
}
