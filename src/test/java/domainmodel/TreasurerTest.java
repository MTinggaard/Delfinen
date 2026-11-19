package domainmodel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TreasurerTest {


    private Member createMember(int age, MemberType type) {
        return new Member(1, "Test", LocalDate.now().minusYears(age), type, new ArrayList<>());
    }

    @Test
    void active15YearOldPays1200() {
        int age = 15;
        Member member = createMember(15, MemberType.PASSIVE);
        MemberShipFee fee = member.getMemberShipFee();
        assertEquals(500, fee.getPrice());
    }

    @Test
    void active17YearOldPays1000() {
        Member member = createMember(17, MemberType.COMPETITIVE);
        MemberShipFee fee = member.getMemberShipFee();

        assertEquals(1000, fee.getPrice());
    }

    @Test
    void active18YearOldPays1600() {
        Member member = createMember(18, MemberType.COMPETITIVE);
        MemberShipFee fee = member.getMemberShipFee();

        assertEquals(1600, fee.getPrice());
    }

    @Test
    void active25YearOldPays1600() {
        Member member = createMember(25, MemberType.COMPETITIVE);
        MemberShipFee fee = member.getMemberShipFee();

        assertEquals(1600, fee.getPrice());
    }

    @Test
    void active60YearOldPays1200() {
        Member member = createMember(60, MemberType.COMPETITIVE);
        MemberShipFee fee = member.getMemberShipFee();

        assertEquals(1200, fee.getPrice());
    }

    @Test
    void active74YearOldPays1200() {
        Member member = createMember(74, MemberType.COMPETITIVE);
        MemberShipFee fee = member.getMemberShipFee();
        assertEquals(1200, fee.getPrice());
    }

    @Test
    void newMemberStartsAsNotPayed(){
        SwimClub swimClub = new SwimClub("guldbar");
        Chairman chairman = new Chairman(swimClub);
        Treasurer treasurer = new Treasurer(swimClub);
       Member member = chairman.createMember("gustav",LocalDate.of(2003,12,12),MemberType.PASSIVE,null);

        assertFalse(treasurer.getPaidinfo(member));
    }
    @Test
    void setMemberAsPayed(){
        SwimClub swimClub = new SwimClub("guldbar");
        Chairman chairman = new Chairman(swimClub);
        Treasurer treasurer = new Treasurer(swimClub);
        Member member = chairman.createMember("gustav",LocalDate.of(2003,12,12),MemberType.PASSIVE,null);

        treasurer.setMemberPayed(member);

        assertTrue(treasurer.getPaidinfo(member));
    }
    @Test
    void tresuererReturnsPrice(){
        SwimClub swimClub = new SwimClub("guldbar");
        Chairman chairman = new Chairman(swimClub);
        Treasurer treasurer = new Treasurer(swimClub);
        Member member = chairman.createMember("gustav",LocalDate.of(2003,12,12),MemberType.PASSIVE,null);

        double fee = treasurer.getMembershipFee(member);
        //we know that it is going to be 500 since he is a pasiv member

        assertEquals(500,fee);
    }


}