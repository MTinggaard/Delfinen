package ui;

import domainmodel.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import static domainmodel.MemberType.EXERCISEER;
import static domainmodel.SwimDisciplin.BREASTSTROKE;

public class Userinterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();
    private boolean running = true;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void startProgram() {
        controller.getChairman().createMember("Ask", LocalDate.of(2003, 11, 18), EXERCISEER, null);
        controller.getChairman().createMember("Gustav", LocalDate.of(2004, 4, 23), EXERCISEER, null);
        controller.getChairman().createMember("Mikkel", LocalDate.of(2004, 9, 23), EXERCISEER, null);

        boolean loggedIn = false;
        System.out.println("Velkommen til Delfinen svømmeklub!");
        while (running) {
            System.out.println("Login");

            System.out.print("Brugernavn: ");
            String username = scanner.nextLine().strip().toLowerCase();

            System.out.print("Password: ");
            String password = scanner.nextLine().strip();
            System.out.println();

            controller.setLoggedInUser(username, password);

            switch (controller.getLoggedInUser()) {
                case CHAIRMAN -> showChairmanMenu();
                case TREASURER -> treasurerMenu();
                case TRAINER -> trainerMenu();
                default -> System.out.println("Ugyldigt login. Prøv igen.");
            }
        }
    }

    private void trainerMenu() {
    }

    private void showChairmanMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("========== FORMAND ==========");
            System.out.println("1: Opret medlem");
            System.out.println("2: Se alle medlemmer");
            System.out.println("3: Log ud");
            System.out.println("0: Stop programmet");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> createMember();
                case "2" -> showMemberList();
                case "3" -> inMenu = false;
                case "0" -> running = false;
                default -> System.out.println("Ugyldigt valg");
            }
        }
    }

    private void createMember() {
        System.out.println("Udfyld følgende for at tilføje et medlem: Navn, fødselsdato, medlemskabstype, discipliner hvis det en konkurrencesvømmer");
        String name;
        LocalDate birthdate;
        MemberType memberType = MemberType.PASSIVE;
        ;
        SwimDisciplin swimDisciplin;

        // Name input
        while (true) {
            System.out.print("Navn: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Navn må ikke være tomt. Prøv igen");
            } else break;
        }

        // Birthdate input
        while (true) {
            birthdate = readDate("Fødselsdato (\"dd-mm-åååå\"): ");
            if (birthdate.isAfter(LocalDate.now())) {
                System.out.println("Fødselsdatoen kan ikke være i fremtiden. Prøv igen");
            } else if (birthdate.isBefore(LocalDate.now().minusYears(100))) {
                System.out.println("Du for gammel til den her klub");
            } else break;
        }

        // MemberType input
        boolean ask = true;
        while (ask) {
            System.out.println("Vælg medlemstype:");
            System.out.println("\t1. Passiv");
            System.out.println("\t2. Motonist");
            System.out.println("\t3. Konkurrencesvømmer");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    memberType = MemberType.PASSIVE;
                    ask = false;
                }
                case "2" -> {
                    memberType = EXERCISEER;
                    ask = false;
                }
                case "3" -> {
                    memberType = MemberType.COMPETITIVE;
                    ask = false;
                }
                default -> {
                    System.out.println("Ugyldigt svar: " + choice);
                    System.out.println("Dit svar skal være enten 1, 2 eller 3");
                }
            }
        }
        ArrayList<SwimDisciplin> swimDiscipline = new ArrayList<>();
        if (memberType == MemberType.COMPETITIVE) {
            while (true) {
                System.out.println("Vælg discipliner svømmeren konkurrere i: ");
                System.out.println("1. Crawl");
                System.out.println("2. Brystsvømning");
                System.out.println("3. Rygcrawl");
                System.out.println("4. Butterfly");
                System.out.println("Indtast numre adskilt af komma (Eksempel 1, 3, 4): ");
                String input = scanner.nextLine();
                String[] choices = input.split(",");
                for (String choice : choices) {
                    choice = choice.strip();
                    if (choice.equals("1")) {
                        swimDiscipline.add(SwimDisciplin.CRAWL);
                    }
                    if (choice.equals("2")) {
                        swimDiscipline.add(BREASTSTROKE);
                    }
                    if (choice.equals("3")) {
                        swimDiscipline.add(SwimDisciplin.BACKSTROKE);
                    }
                    if (choice.equals("4")) {
                        swimDiscipline.add(SwimDisciplin.BUTTERFLY);
                    }
                }
                if (!swimDiscipline.isEmpty()) {
                    break;
                } else System.out.println("En konkurrencesvømmer skal mindst have en disciplin. Prøv igen");
            }
        }
        try {
            Member newMember = controller.createMember(name, birthdate, memberType, swimDiscipline);
            System.out.println("Medlem oprettet:");
            System.out.println(" - Medlemsnummer: " + newMember.getMemberNumber());
            System.out.println(" - Navn: " + newMember.getName());
            System.out.println(" - Fødselsdato: " + newMember.getBirthdate().format(formatter));
            System.out.println(" - Medlemstype: " + newMember.getMemberType());
            if (newMember.getMemberType() == MemberType.COMPETITIVE) {
                System.out.print(" - Svømmediscipliner: ");
                System.out.println(newMember.getSwimDisciplin().toString().replace("[", "").replace("]", ""));
            }
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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

    private void treasurerMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("========== Kasserer ==========");
            System.out.println("1. se alle medlemers betaling");
            System.out.println("2. rediger medlemmers betaling");
            System.out.println("0. Log ud");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showMembershipFees();
                case "2" -> editPayment();
                case "0" -> inMenu = false;
                default -> System.out.println("den komando kendes ikke");
            }
        }
    }

    public void showMemberList() {
        ArrayList<Member> members = controller.getMemberList();

        if (members.isEmpty()) {
            System.out.println("Der er ingen registrerede medlemmer");
        } else {
            System.out.printf("%-6s %-20s %-10s %-10s %-20s%n", "Nr.", "Navn", "Alder", "Hold", "Type");
            System.out.println("-----------------------------------------------------------");

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
                } else if (member.getMemberType() == EXERCISEER) {
                    memberType = "Motionist";
                } else {
                    memberType = "Konkurrencesvømmer";
                }
                System.out.printf("%-6d %-20s %-10s %-10s %-20s%n",
                        member.getMemberNumber(),
                        member.getName(),
                        member.getAge() + " år",
                        seniorOrJunior,
                        memberType);
            }
            System.out.println();
        }
    }

    public void showMembershipFees() {
        for (Member member : controller.getMemberList()) {
            System.out.println(member.getName() + " (" + member.getMemberNumber() + "): "
                    + controller.getMemberShipFee(member) + " kr. har de betalt:" + translatePaid(controller.getPaidInfo(member)));
        }
    }

    public void editPayment() {
        boolean fundet = false;
        Member chosenMember = null;
        System.out.println("søg for et medlem med medlems id");
        int memberShipNumber = scanner.nextInt();
        scanner.nextLine();
        for (Member member : controller.getMemberList()) {
            if (member.getMemberNumber() == memberShipNumber) {
                chosenMember = member;
                fundet = true;
            }
        }
        if (!fundet) {
            System.out.println("findes ikke et medlem med det medlems nummer");
        }
        if (fundet) {
            System.out.println("du har valgt " + chosenMember.getName() + ". person id:" + chosenMember.getMemberNumber() + ". " + controller.getMemberShipFee(chosenMember) + "Kr: " + translatePaid(controller.getPaidInfo(chosenMember)));
            System.out.println("ville du ændre hans status til betalt (ja/nej)");
            String comand = scanner.nextLine().toLowerCase().trim();
            switch (comand) {
                case "ja" -> controller.setMemberHasPayed(chosenMember);
                case "nej" -> System.out.println("ingenting er blevet ændret");
                default -> System.out.println("den komando kan ikke bruges");
            }
            System.out.println(chosenMember.getName() + ". " + chosenMember.getMemberNumber() + ". " + controller.getMemberShipFee(chosenMember) + "kr: " + translatePaid(controller.getPaidInfo(chosenMember)));
        }
    }

    public String translatePaid(boolean trueOrFalse) {
        if (trueOrFalse){
            return("betalt");
        }
        else {
            return("ikke betalt");
        }
    }

}
