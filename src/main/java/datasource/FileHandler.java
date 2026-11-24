package datasource;

import domainmodel.Member;
import domainmodel.MemberType;
import domainmodel.SwimDisciplin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private String fileName;
    private int skippedLineCount;
    private static final String HEADER = "memberNumber;name;birthdate;memberType;swimDisciplin";


    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public void saveMembersInFile(ArrayList<Member> members) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));
        output.println(HEADER);
        for (Member member : members) {
            output.println(toCsvLine(member));
        }
        output.close();
    }

    private String toCsvLine(Member member) {
        return member.getMemberNumber() + ";"
                + member.getName() + ";"
                + member.getBirthdate() + ";"
                + member.getMemberType() + ";"
                + member.getSwimDisciplin() + ";";
    }

    private Member parseMember(String line) {
        String[] fields = line.split(";", -1);

        if (fields.length != 5) {
            return null;
        }

        try {
            int memberNumber = Integer.parseInt(fields[0]);
            String name = fields[1];
            LocalDate birthdate = LocalDate.parse(fields[2]);
            MemberType membertype = MemberType.valueOf(fields[3]);
            if (membertype == MemberType.COMPETITIVE) {
                ArrayList<SwimDisciplin> swimDisciplins = new ArrayList<>();

                String[] disciplins = fields[4].split(",");
                for (String disciplin : disciplins) {
                    swimDisciplins.add(SwimDisciplin.valueOf(disciplin));
                }
                return new Member(memberNumber, name, birthdate, membertype, swimDisciplins);
            } else return new Member(memberNumber, name, birthdate, membertype, null);

        } catch (IllegalArgumentException | DateTimeParseException e) {
            return null;
        }
    }


    public ArrayList<Member> loadMembers() throws FileNotFoundException {
        ArrayList<Member> members = new ArrayList<>();
        skippedLineCount = 0;

        Scanner scanner = new Scanner(new File(fileName));

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Member member = parseMember(line);

            if (member == null) {
                skippedLineCount++;
            } else {
                members.add(member);
            }
        }

        scanner.close();
        return members;
    }

    public int getSkippedLineCount() {
        return skippedLineCount;
    }
}

