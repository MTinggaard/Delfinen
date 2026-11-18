package domainmodel;

import static domainmodel.MemberType.PASSIVE;

public class MemberShipFee {
    private boolean paid;
    private double price;

    public MemberShipFee(int age, Enum membertype) {
        this.price = calculatePrice(age,membertype);
        this.paid = false;
    }

    public double calculatePrice(int age, Enum MemberType) {
        if (MemberType == PASSIVE) {
            price = 500;
            return price;
        } else if (age < 18) {
            price = 1000;
            return price;
        } else if (age >= 18 && age < 60) {
            price = 1600;
            return price;
        } else if ( age > 60) {
            price = 1600 * 0.75;
            return price;
        }
        return 0;
    }

    public double getPrice() {
        return price;
    }

    public void setIsPaid(boolean paid) {
        this.paid = paid;
    }
}
