package domainmodel;

import java.time.LocalDate;

public class SwimResult {

    private SwimDisciplin disciplin;
    private int time;
    private LocalDate date;



    public SwimDisciplin getDisciplin(){
        return disciplin;
    }

    public int getTime(){
        return time;
    }

    public LocalDate getDate(){
        return date;
    }
}
