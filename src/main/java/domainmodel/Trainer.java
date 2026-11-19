package domainmodel;

public class Trainer {
    private SwimClub swimClub;

    public Trainer(SwimClub swimClub) {
        this.swimClub = swimClub;
    }


    public RegistrationOutcome registerResult(Member member, SwimResult newResult) {
        if (member.getMemberType() != MemberType.COMPETITIVE) {
            return RegistrationOutcome.NOT_COMPETITIVE;
        }
        return member.registerResult(newResult);
    }
}
