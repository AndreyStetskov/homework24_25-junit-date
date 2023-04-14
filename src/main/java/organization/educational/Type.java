package organization.educational;

public enum Type {
    PRE_SCHOOL("Creche, Nurse school or Preschool"),
    ELEMENTARY_SCHOOL("Elementary school"),
    MIDDLE_SCHOOL("Middle school"),
    HIGH_SCHOOL("High school"),
    UNIVERSITY("College or university");

    private final String SCHOOL;

    Type(String school) {
        this.SCHOOL = school;
    }

    public String getSchool() {
        return SCHOOL;
    }
}
