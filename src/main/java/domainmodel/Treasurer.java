package domainmodel;

public class Treasurer {
    private SwimClub swimClub;

    public Treasurer(SwimClub swimClub) {
        this.swimClub = swimClub;
    }

    public void showMembershipFees() {
        for (Member member : swimClub.getMembers()) {
            System.out.println(member.getName() + "(" + member.getMemberNumber() + "): " + member.getMemberShipFee().getPrice() + " kr.");
        }
    }
}
