package datasource;

import domainmodel.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    private static final String TEST_FILE = "test-members.csv";
    private FileHandler fileHandlerTest;


    @BeforeEach
    void setUp() {
        fileHandlerTest = new FileHandler(TEST_FILE);
    }

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void savedMembersCanBeLoadedAgain() throws FileNotFoundException {
        //Arrange

        SwimClub swimClub = new SwimClub("Test");
        Member member = new Member(1, "TestDummy", LocalDate.of(2004, 9, 23), MemberType.PASSIVE, null);
        ArrayList<SwimDisciplin> disciplins = new ArrayList<>();
        disciplins.add(SwimDisciplin.CRAWL);
        disciplins.add(SwimDisciplin.BUTTERFLY);
        Member member2 = new Member(2, "TestDummy2", LocalDate.of(2004, 9, 23), MemberType.COMPETITIVE, disciplins);
        swimClub.addMember(member);
        swimClub.addMember(member2);

        // Act
        fileHandlerTest.saveMembersInFile(swimClub.getMembers());
        ArrayList<Member> loaded = fileHandlerTest.loadMembers();

        // Assert
        assertEquals(2, loaded.size());
        Member member1Result = loaded.getFirst();
        Member member2Result = loaded.get(1);

        // MemberNumber test
        assertEquals(member.getMemberNumber(), member1Result.getMemberNumber());
        assertEquals(member2.getMemberNumber(), member2Result.getMemberNumber());

        // MemberName test
        assertEquals(member.getName(), member1Result.getName());
        assertEquals(member2.getName(), member2Result.getName());

        // MemberBirthdate test
        assertEquals(member.getBirthdate(), member1Result.getBirthdate());
        assertEquals(member2.getBirthdate(), member2Result.getBirthdate());

        // MemberType test
        assertEquals(member.getMemberType(), member1Result.getMemberType());
        assertEquals(member2.getMemberType(), member2Result.getMemberType());

        // SwimDisciplin test
        assertEquals(member.getSwimDisciplin(), member1Result.getSwimDisciplin());
        assertEquals(member2.getSwimDisciplin(), member2Result.getSwimDisciplin());
    }

    @Test
    void emptyListCanBeSavedAndLoaded() throws FileNotFoundException {
        // Arrange
        SwimClub swimClub = new SwimClub("Test");

        // Act
        fileHandlerTest.saveMembersInFile(swimClub.getMembers());
        ArrayList<Member> loaded = fileHandlerTest.loadMembers();

        // Assert
        assertEquals(0, loaded.size());
    }

    @Test
    void loadedMissingFileThrowsException() throws FileNotFoundException {
        File file = new File(TEST_FILE);

        FileHandler fileHandler = new FileHandler(TEST_FILE);

        assertFalse(file.exists());
        assertThrows(FileNotFoundException.class, () -> {
            fileHandler.loadMembers();
        });
    }

    @Test
    void invalidLinesAreSkipped() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(TEST_FILE));
        output.println("memberNumber;name;birthdate;memberType;swimDisciplin");
        output.println("1;Ask Haue;2003-11-18;EXERCISEER;null;");
        output.println("Kun;to;felter");
        output.println("3;Mikkel Tinggaard;IkkeEnDato;EXERCISEER;null;");
        output.close();

        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Member> members = fileHandler.loadMembers();

        assertEquals(1, members.size());
        assertEquals("Ask Haue",members.get(0).getName());
        assertEquals(2, fileHandler.getSkippedLineCount());
    }
}


