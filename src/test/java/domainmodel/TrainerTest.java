package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {
    private SwimClub swimClub;
    private Chairman chairman;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        swimClub = new SwimClub("Delfinen");
        chairman = new Chairman(swimClub);
        trainer = new Trainer(swimClub);
    }

    @Test
    void competitive17IsJunior() {
        // Arrange
        Member member = chairman.createMember("Anna", LocalDate.now().minusYears(17),
                MemberType.COMPETITIVE, new ArrayList<>());

        // Assert
        assertTrue(trainer.getJuniorMembers().contains(member));
        assertFalse(trainer.getSeniorMembers().contains(member));
    }

    @Test
    void competitive18IsSenior() {

        // Arrange
        Member member = chairman.createMember("Kristian", LocalDate.now().minusYears(18),
                MemberType.COMPETITIVE, new ArrayList<>());

        // Assert
        assertFalse(trainer.getJuniorMembers().contains(member));
        assertTrue(trainer.getSeniorMembers().contains(member));
    }

    @Test
    void exerciserNoTeam() {

        // Arrange
        Member member = chairman.createMember("Filip", LocalDate.now().minusYears(20),
                MemberType.EXERCISEER, new ArrayList<>());

        // Assert
        assertFalse(trainer.getJuniorMembers().contains(member));
        assertFalse(trainer.getSeniorMembers().contains(member));
    }
    @Test
    void passiveNoTeam() {

        // Arrange
        Member member = chairman.createMember("Torben", LocalDate.now().minusYears(19),
                MemberType.PASSIVE, new ArrayList<>());

        // Assert
        assertFalse(trainer.getJuniorMembers().contains(member));
        assertFalse(trainer.getSeniorMembers().contains(member));
    }
}