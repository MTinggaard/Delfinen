package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(1, swimClub.getMemberCount());
    }
}
