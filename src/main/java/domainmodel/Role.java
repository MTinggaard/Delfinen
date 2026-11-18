package domainmodel;

public enum Role {
    CHAIRMAN,
    TREASURER,
    TRAINER,
    DENIEDUSER;

    @Override
    public String toString() {
        switch (this){
            case CHAIRMAN -> {
                return "formand";
            }
            case TREASURER -> {
                return "kasserer";
            }
            case TRAINER -> {
                return "træner";
            }
        }
        return "";
    }
}
