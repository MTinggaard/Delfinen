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
    void setUp(){
        swimClub = new SwimClub("Delfinen");
        chairman = new Chairman(swimClub);
    }

    @Test
    void createMemberTest(){
        // Act
        chairman.createMember("Gustav", LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);

        // Assert
        assertEquals(1, swimClub.getMemberCount());
    }

    @Test
    void createMoreMembers(){
        // Arrange
        ArrayList<SwimDisciplin> disciplins = new ArrayList<>();
        disciplins.add(SwimDisciplin.BUTTERFLY);
        disciplins.add(SwimDisciplin.CRAWL);

        // Act
        chairman.createMember("Gustav", LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);
        chairman.createMember("Ask", LocalDate.of(2003, 11, 18), MemberType.COMPETITIVE,  disciplins);

        // Assert
        assertEquals(2, swimClub.getMemberCount());
        assertEquals(1 , swimClub.getMembers().getFirst().getMemberNumber());
        assertEquals(2 , swimClub.getMembers().get(1).getMemberNumber());
        assertNotEquals(swimClub.getMembers().get(0).getMemberNumber(), swimClub.getMembers().get(1).getMemberNumber());
    }

    @Test
    void createMemberWithEmptyName(){
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember("", LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);
        });
        assertEquals(0, swimClub.getMemberCount());
    }

    @Test
    void createMemberWithNullName(){
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember(null, LocalDate.of(2004, 5, 23), MemberType.PASSIVE, null);
        });
        assertEquals(0, swimClub.getMemberCount());
    }

    @Test
    void createMemberWithFutureBirthdate(){
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember("Gustav", LocalDate.now().plusDays(1), MemberType.PASSIVE, null);
        });
        assertEquals(0, swimClub.getMemberCount());
    }

    @Test
    void createMemberWithNullBirthdate(){
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember("Gustav", null, MemberType.PASSIVE, null);
        });
        assertEquals(0, swimClub.getMemberCount());
    }

    @Test
    void createMemberWithNullMemberType(){
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chairman.createMember("Gustav", LocalDate.now(), null, null);
        });
        assertEquals(0, swimClub.getMemberCount());
    }

    @Test
    void competitiveSwimmersDisciplinesSaved(){
        // Arrange
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(SwimDisciplin.BUTTERFLY);
        disciplines.add(SwimDisciplin.CRAWL);

        // Act
        Member member = chairman.createMember("Gustav", LocalDate.of(2004, 5, 23), MemberType.COMPETITIVE, disciplines);

        // Assert
        assertEquals(disciplines, member.getSwimDisciplin());

    }
}
