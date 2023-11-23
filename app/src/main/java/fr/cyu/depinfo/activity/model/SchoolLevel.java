package fr.cyu.depinfo.activity.model;

public enum SchoolLevel {
    YEAR1("CP"),
    YEAR2("CE1"),
    YEAR3("CE2"),
    YEAR4("CM1"),
    YEAR5("CM2"),
    YEAR6("6E"),
    YEAR7("5E"),
    YEAR8("4E"),
    YEAR9("3E"),
    YEAR10("SECONDE"),
    YEAR11("PREMIERE"),
    YEAR12("TERMINALE");

    private final String year;

    SchoolLevel(String year) {
        this.year = year;
    }

    public static SchoolLevel fromYear(String year) {
        return switch (year) {
            case "CP" -> YEAR1;
            case "CE1" -> YEAR2;
            case "CE2" -> YEAR3;
            case "CM1" -> YEAR4;
            case "CM2" -> YEAR5;
            case "6E" -> YEAR6;
            case "5E" -> YEAR7;
            case "4E" -> YEAR8;
            case "3E" -> YEAR9;
            case "SECONDE" -> YEAR10;
            case "PREMIERE" -> YEAR11;
            case "TERMINALE" -> YEAR12;
            default -> null;
        };
    }

    public String getYear() {
        return this.year;
    }
}
