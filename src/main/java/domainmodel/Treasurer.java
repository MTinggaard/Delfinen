package domainmodel;

public class Treasurer {
    private SwimClub swimClub;

    public Treasurer(SwimClub swimClub) {
        this.swimClub = swimClub;
    }

    public double getMembershipFee(Member member) {
        return member.getMemberShipFee().getPrice();
    }


    public boolean setMemberPayed(Member member) {
        return member.getMemberShipFee().isPaid();
    }
}
