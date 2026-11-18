package ui;

import domainmodel.Controller;
import domainmodel.Member;
import domainmodel.MemberType;
import domainmodel.SwimDisciplin;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Userinterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();
    private boolean running = true;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void startProgram() {
        System.out.println("Velkommen til Delfinen svømmeklub!");
        printMenu();
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> {
                while (running) {
                    chairmanView();
                }
            }
            case "2" -> {
                while (running) {
                    treasurerView();
                }
            }
            case "3" -> {
                while (running) {
                    trainerView();
                }
            }
            case "0" -> running = false;
            default -> System.out.println("Ukendt valg: " + choice);
        }

    }

    private void printMenu() {
        System.out.println("Vælg din funktion i klubben:");
        System.out.println("Tast 0 for at stoppe programmet");
        System.out.println("Tast 1 for formand");
        System.out.println("Tast 2 for kasserer");
        System.out.println("Tast 3 for træner");
    }

    private void chairmanView() {
        System.out.println("Formand funktioner:");
        System.out.println("1: Opret medlem");
        System.out.println("2: Se alle medlemmer");
        System.out.println("3: Stop programmet");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1" -> createMember();
            case "2" -> showMemberList();
        }
    }

    private void createMember() {
        System.out.println("Udfyld følgende for at tilføje et medlem: Navn, fødselsdato, medlemskabstype, discipliner hvis det en konkurrencesvømmer");
        System.out.print("Navn: ");
        String name = scanner.nextLine();

        LocalDate birthdate = readDate("Fødselsdato (\"dd-mm-åååå\"): ");

        System.out.println("Vælg medlemstype:");
        System.out.println("\t 1. Passiv");
        System.out.println("\t 2. Motonist");
        System.out.println("\t 3. Konkurrencesvømmer");
        MemberType memberType = MemberType.PASSIVE;
        boolean ask = true;
        while (ask) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    memberType = MemberType.PASSIVE;
                    ask = false;
                }
                case "2" -> {
                    memberType = MemberType.EXERCISEER;
                    ask = false;
                }
                case "3" -> {
                    memberType = MemberType.COMPETITIVE;
                    ask = false;
                }
                default -> System.out.println("Ugyldigt svar: " + choice);
            }
        }
        ArrayList<SwimDisciplin> swimDiscipline = new ArrayList<>();
        if (memberType == MemberType.COMPETITIVE) {
            System.out.println("Vælg discipliner svømmeren konkurrere i: ");
            System.out.println("1. Crawl");
            System.out.println("2. Brystsvømning");
            System.out.println("3. Rygcrawl");
            System.out.println("4. Butterfly");
            System.out.println("Indtast numre adskilt af komma (Eksempel 1, 3, 4): ");
            String input = scanner.nextLine();
            String[] choices = input.split(",");
            for (String choice : choices) {
                if (choice.equals("1")) {
                    swimDiscipline.add(SwimDisciplin.CRAWL);
                }
                if (choice.equals("2")) {
                    swimDiscipline.add(SwimDisciplin.BREASTSTROKE);
                }
                if (choice.equals("3")) {
                    swimDiscipline.add(SwimDisciplin.BACKCRAWL);
                }
                if (choice.equals("4")) {
                    swimDiscipline.add(SwimDisciplin.BUTTERFLY);
                }
            }
        }

        controller.createMember(name, birthdate, memberType, swimDiscipline);
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return LocalDate.now();
            }

            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("\"" + input + "\" er ikke et gyldigt datoformat. Prøv igen.");
            }
        }
    }

    private void treasurerView() {

    }

    private void trainerView() {

    }

    public void showMemberList() {
        ArrayList<Member> members = controller.getMemberList();

        if (members.isEmpty()) {
            System.out.println("Der er ingen registrerede medlemmer");
        } else {
            for (Member member : members) {
                String seniorOrJunior;
                String memberType;
                if (controller.isJunior(member)) {
                    seniorOrJunior = "Junior";
                } else {
                    seniorOrJunior = "Senior";
                }

                if (member.getMemberType() == MemberType.PASSIVE) {
                    memberType = "Passiv";
                } else if (member.getMemberType() == MemberType.EXERCISEER) {
                    memberType = "Motionist";
                } else {
                    memberType = "Konkurrencesvømmer";
                }

                System.out.println(member.getMemberNumber() + ", " + member.getName() + ", "
                        + member.getAge() + " år, " + seniorOrJunior + ", " + memberType);
            }
        }
    }

    public void showMembershipFees() {
        for (Member member : controller.getMemberList()) {
            System.out.println(member.getName() + " (" + member.getMemberNumber() + "): "
                    + controller.getMemberShipFee(member) + " kr.");
        }
    }
}
