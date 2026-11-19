package domainmodel;

import java.time.LocalDate;

public class TrainingResult extends SwimResult {
    public TrainingResult(SwimDisciplin discipline, int time, LocalDate date) {
        super(discipline, time, date);
    }
}
