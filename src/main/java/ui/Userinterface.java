package ui;

import domainmodel.Chairman;
import domainmodel.Controller;
import domainmodel.SwimClub;

import java.util.Scanner;

public class Userinterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();
    private boolean running = true;

    public void startProgram() {
        SwimClub swimClub = new SwimClub("Delfinen");
        Chairman chairman = new Chairman(swimClub);

        System.out.println("Velkommen til Delfinen svømmeklub!");
        printMenu();
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> chairmanView();
            case "2" -> treasurerView();
            case "3" -> trainerView();
            case "0" -> running = false;
            default -> System.out.println("Ukendt valg: " + choice);
        }

    }

    private void printMenu(){
        System.out.println("Vælg din funktion i klubben:");
        System.out.println("Tast 0 for at stoppe programmet");
        System.out.println("Tast 1 for formand");
        System.out.println("Tast 2 for kasserer");
        System.out.println("Tast 3 for træner");
    }

    private void chairmanView(){
        System.out.println("Formand funktioner:");
        System.out.println("1: Opret medlem");
        System.out.println("2: Se alle medlemmer");

        String choice = scanner.nextLine().trim();
        switch (choice){
            case "1" -> System.out.println("Funktion kommer");
            case "2" -> System.out.println("Funktion kommer");
        }
    }

    private void treasurerView(){

    }

    private void trainerView(){

    }

}
