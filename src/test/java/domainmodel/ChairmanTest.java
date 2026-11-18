package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChairmanTest {

    private SwimClub swimClub;
    private Chairman chairman;

    @BeforeEach
    void setUp() {
        swimClub = new SwimClub("Delfinen");
        chairman = new Chairman(swimClub);
    }

    @Test
    void createMemberTest() {
        // Act
        chairman.createMember("Gustav", LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);

        // Assert
        assertEquals(1, swimClub.getMemberCount());
    }

    @Test
    void createMoreMembers() {
        // Arrange
        ArrayList<SwimDisciplin> disciplins = new ArrayList<>();
        disciplins.add(SwimDisciplin.BUTTERFLY);
        disciplins.add(SwimDisciplin.CRAWL);

        // Act
        chairman.createMember("Gustav", LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);
        chairman.createMember("Ask", LocalDate.of(2003, 11, 18), MemberType.COMPETITIVE, disciplins);

        // Assert
        assertEquals(2, swimClub.getMemberCount());
        assertEquals(1, swimClub.getMembers().getFirst().getMemberNumber());
        assertEquals(2, swimClub.getMembers().get(1).getMemberNumber());
        assertNotEquals(swimClub.getMembers().get(0).getMemberNumber(), swimClub.getMembers().get(1).getMemberNumber());
    }

    @Test
    void createInvalidMember() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember("", LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);
        });
        assertEquals(0, swimClub.getMemberCount());

        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember(null, LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);
        });

    }



























    @Test
    void memberUnder18IsJunior() {
        Member member = chairman.createMember("Anna", LocalDate.now().minusYears(17),
                MemberType.COMPETITIVE, new ArrayList<>());

        assertTrue(chairman.isJunior(member));
    }

    @Test
    void memberOver18IsSenior(){
        Member member = chairman.createMember("Anna", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, new ArrayList<>());

        assertFalse(chairman.isJunior(member));
    }

    @Test
    void emptyClubHasNoMembers() {
        assertTrue(chairman.getMemberList().isEmpty());
    }

    @Test
    void passivePrintsToo(){
        Member member1 = chairman.createMember("Anna", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, new ArrayList<>());
        Member member = chairman.createMember("Lasse", LocalDate.now().minusYears(19),
                MemberType.PASSIVE, new ArrayList<>());
        assertEquals(2, chairman.getMemberList().size());
    }
}