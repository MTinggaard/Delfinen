package domainmodel;

import java.time.LocalDate;

public class CompetitionResult extends SwimResult {
    private String competitionName;
    private int placement;

    public CompetitionResult(SwimDisciplin disciplin, int time, LocalDate date, String competitionName, int placement) {
        super(disciplin, time, date);
        if(competitionName == null || competitionName.strip().isBlank()){
            throw new IllegalArgumentException("Stævnet skal have et navn");
        }
        if(placement < 1){
            throw new IllegalArgumentException("Placering skal være 1 eller højere");
        }
        this.competitionName = competitionName;
        this.placement = placement;
    }

    public void setCompetitionName(String competitionName) {
        if(competitionName == null || competitionName.isBlank()){
            throw new IllegalArgumentException("Stævnet skal have et navn");
        }
         this.competitionName = competitionName;
    }

    public void setPlacement(int placement) {
        if(placement < 1){
            throw new IllegalArgumentException("Placering skal være 1 eller højere");
        }
         this.placement = placement;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public int getPlacement() {
        return placement;
    }
}
