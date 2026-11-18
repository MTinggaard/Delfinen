package datasource;

import domainmodel.Chairman;
import domainmodel.SwimClub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChairmanTest {

    @BeforeEach
    void setUp(){
        SwimClub swimClub = new SwimClub("Delfinen");
        Chairman chairman = new Chairman(swimClub);
    }

    @Test
    void createMemberTest(){
        // Arrange

        // Act

        // Assert
    }
}
