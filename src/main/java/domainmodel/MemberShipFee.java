package domainmodel;

public class MemberShipFee {
    private boolean paid;
    private double price;

    public MemberShipFee(Member member) {
        this.price = calculatePrice(member);
        this.paid = false;
    }

    public double calculatePrice(Member member) {
        if (member.getMemberType().equals(MemberType.PASSIVE)) {
            price = 500;
            return price;
        } else if (member.getAge() < 18) {
            price = 1000;
            return price;
        } else if (member.getAge() >= 18 && member.getAge() < 60) {
            price = 1600;
            return price;
        } else if (member.getAge() > 60) {
            price = 1600 * 0.75;
            return price;
        }
        return 0;
    }

    public double getPrice() {
        return price;
    }
}
