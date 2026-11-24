package domainmodel;

public enum SwimDisciplin {
    BUTTERFLY,
    CRAWL,
    BACKSTROKE,
    BREASTSTROKE;

    @Override
    public String toString() {
        switch (this){
            case CRAWL -> {
                return "Crawl";
            }
            case BUTTERFLY -> {
                return "Butterfly";
            }
            case BREASTSTROKE -> {
                return "Brystsvømning";
            }
            case BACKSTROKE -> {
                return "Rygcrawl";
            }
        }
        return "";
    }

    public String toStringFileHandling(){
        switch (this){
            case CRAWL -> {
                return "CRAWL";
            }
            case BUTTERFLY -> {
                return "BUTTERFLY";
            }
            case BREASTSTROKE -> {
                return "BREASTSTROKE";
            }
            case BACKSTROKE -> {
                return "BACKSTROKE";
            }
        }
        return "";
    }
}
