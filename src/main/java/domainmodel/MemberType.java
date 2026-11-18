package domainmodel;

public enum MemberType {
    PASSIVE,
    EXERCISEER,
    COMPETITIVE;

    @Override
    public String toString() {
        switch (this){
            case EXERCISEER -> {
                return "Motionist";
            }
            case PASSIVE -> {
                return "Passiv";
            }
            case COMPETITIVE -> {
                return "Konkurrencesvømmer";
            }
        }
        return "";
    }
}
