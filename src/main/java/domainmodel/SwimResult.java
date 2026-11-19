package domainmodel;

import java.time.LocalDate;

public abstract class SwimResult {
    private SwimDisciplin discipline;
    private int time;
    private LocalDate date;


    public SwimResult(SwimDisciplin discipline, int time, LocalDate date) {
        if (time < 1){
            throw new IllegalArgumentException("Svømmetid skal være større end 0");
        }
        if (date == null){
            throw new IllegalArgumentException("Dato skal være udfyldt");
        }
        if (discipline == null){
            throw new IllegalArgumentException("Disciplin skal være udfyldt");
        }

        this.discipline = discipline;
        this.time = time;
        this.date = date;
    }

    public SwimDisciplin getDiscipline() {
        return discipline;
    }

    public int getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}