package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static domainmodel.RegistrationOutcome.*;
import static domainmodel.SwimDisciplin.BUTTERFLY;
import static domainmodel.SwimDisciplin.CRAWL;
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
    @Test
    void betterTimeBecomesNewBest(){
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Torben", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, disciplines);

        TrainingResult oldResult = new TrainingResult(CRAWL,6532, LocalDate.now().minusDays(7));
        trainer.registerResult(member, oldResult);

        TrainingResult newResult = new TrainingResult(CRAWL,6410, LocalDate.now());
        RegistrationOutcome outcome = trainer.registerResult(member, newResult);
        assertEquals(NEW_BEST, outcome);

        assertEquals(6410, member.getTrainingResultFor(CRAWL).getTime());
        assertEquals(LocalDate.now(), member.getTrainingResultFor(CRAWL).getDate());
    }
    @Test
    void worseTimeIsNotNewBest(){
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Filip", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, disciplines);

        TrainingResult oldResult = new TrainingResult(CRAWL,6532, LocalDate.now().minusDays(7));
        trainer.registerResult(member, oldResult);

        TrainingResult newResult = new TrainingResult(CRAWL,6650, LocalDate.now());
        RegistrationOutcome outcome = trainer.registerResult(member, newResult);
        assertEquals(NOT_BETTER, outcome);

        assertEquals(6532, member.getTrainingResultFor(CRAWL).getTime());
        assertEquals(LocalDate.now().minusDays(7), member.getTrainingResultFor(CRAWL).getDate());
    }
    @Test
    void sameTimeIsNotNewBest(){
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Kristian", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, disciplines);

        TrainingResult oldResult = new TrainingResult(CRAWL,6532, LocalDate.now().minusDays(7));
        trainer.registerResult(member, oldResult);

        TrainingResult newResult = new TrainingResult(CRAWL,6532, LocalDate.now());
        RegistrationOutcome outcome = trainer.registerResult(member, newResult);
        assertEquals(NOT_BETTER, outcome);

        assertEquals(6532, member.getTrainingResultFor(CRAWL).getTime());
        assertEquals(LocalDate.now().minusDays(7), member.getTrainingResultFor(CRAWL).getDate());
    }
    @Test
    void firstTimeBecomesBest(){
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Kristian", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, disciplines);

        TrainingResult newResult = new TrainingResult(CRAWL,7000, LocalDate.now());
        RegistrationOutcome outcome = trainer.registerResult(member, newResult);
        assertEquals(NEW_BEST, outcome);

        assertEquals(7000, member.getTrainingResultFor(CRAWL).getTime());
        assertEquals(LocalDate.now(), member.getTrainingResultFor(CRAWL).getDate());
    }
    @Test
    void notActiveDisciplineIsRejected(){
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Kristian", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, disciplines);

        TrainingResult newResult = new TrainingResult(BUTTERFLY,7000, LocalDate.now());
        RegistrationOutcome outcome = trainer.registerResult(member, newResult);
        assertEquals(NOT_ACTIVE, outcome);

        assertNull(member.getTrainingResultFor(BUTTERFLY));
    }
    @Test
    void validCompetitionResultIsRegistered() {
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Torben", LocalDate.now().minusYears(19),
                MemberType.COMPETITIVE, disciplines);

        CompetitionResult result = new CompetitionResult(CRAWL, 6532, LocalDate.now(), "DM", 1);


        RegistrationOutcome outcome = trainer.registerResult(member, result);


        assertEquals(SUCCESS, outcome);
        assertTrue(member.getCompetitionResults().contains(result));
    }
    @Test
    void exerciserCompetitionResultIsRejected() {
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Torben", LocalDate.now().minusYears(19),
                MemberType.EXERCISEER, disciplines);

        CompetitionResult result = new CompetitionResult(CRAWL, 6532, LocalDate.now(), "DM", 1);


        RegistrationOutcome outcome = trainer.registerResult(member, result);


        assertEquals(NOT_COMPETITIVE, outcome);
        assertFalse(member.getCompetitionResults().contains(result));
    }
    @Test
    void passiveCompetitionResultIsRejected() {
        ArrayList<SwimDisciplin> disciplines = new ArrayList<>();
        disciplines.add(CRAWL);

        Member member = chairman.createMember("Torben", LocalDate.now().minusYears(19),
                MemberType.PASSIVE, disciplines);

        CompetitionResult result = new CompetitionResult(CRAWL, 6532, LocalDate.now(), "DM", 1);


        RegistrationOutcome outcome = trainer.registerResult(member, result);


        assertEquals(NOT_COMPETITIVE, outcome);
        assertFalse(member.getCompetitionResults().contains(result));
    }
    @Test
    void invalidPlacementThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> new CompetitionResult(CRAWL, 6532, LocalDate.now(), "VM", 0));
    }
    @Test
    void invalidTimeThrowsException(){
        assertThrows(IllegalArgumentException.class, () -> new CompetitionResult(CRAWL, 0, LocalDate.now(), "VM", 1));
    }
    @Test
    void unknownMemberNumberIsNotFound(){
        Member member = chairman.createMember("Torben", LocalDate.now().minusYears(19),
                MemberType.PASSIVE, new ArrayList<>());

        assertNull(swimClub.findMember(999));


    }
}