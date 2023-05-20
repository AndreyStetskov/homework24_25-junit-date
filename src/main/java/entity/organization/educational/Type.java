package entity.organization.educational;

import console.OutputMassage;

public enum Type {
    PRE_SCHOOL("Creche, Nurse school or Preschool", 0, 6),
    ELEMENTARY_SCHOOL("Elementary school", 6, 12),
    MIDDLE_SCHOOL("Middle school", 12, 15),
    HIGH_SCHOOL("High school", 15, 18),
    UNIVERSITY("College or university", 18, 90);

    private final String SCHOOL;
    private final int AGE_MIN;
    private final int AGE_MAX;


    Type(String school, int ageMin, int ageMax) {
        this.SCHOOL = school;
        this.AGE_MIN = ageMin;
        this.AGE_MAX = ageMax;
    }

    public void recommended(int age) {
        OutputMassage massage = new OutputMassage();

        if (age < PRE_SCHOOL.AGE_MAX) massage.recommendedPreSchool();
        else if (age < ELEMENTARY_SCHOOL.AGE_MAX) massage.recommendedElementarySchool();
        else if (age < MIDDLE_SCHOOL.AGE_MAX) massage.recommendedMiddleSchool();
        else if (age < HIGH_SCHOOL.AGE_MAX) massage.recommendedHighSchool();
        else if (age < UNIVERSITY.AGE_MAX) massage.recommendedUniversity();
        else massage.notRecommendedToStudy();
    }


    public String getSchool() {
        return SCHOOL;
    }

    public int getMinAge() {
        return AGE_MIN;
    }

    public int getMaxAge() {
        return AGE_MAX;
    }
}
