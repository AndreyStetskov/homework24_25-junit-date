package entity.organization.educational;

import entity.organization.Organization;

import java.util.Objects;

public class EducationalDTO extends Organization {
    private String ageLimit;

    public EducationalDTO(String[] data) {
        super(Integer.parseInt(data[0]), data[1], data[2], data[4], Boolean.parseBoolean(data[5]), Integer.parseInt(data[6]), data[7], data[8], data[9], data[10], Boolean.parseBoolean(data[11]));
        this.ageLimit = data[3];
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EducationalDTO that = (EducationalDTO) o;
        return Objects.equals(ageLimit, that.ageLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ageLimit);
    }

    @Override
    public String toString() {
        String stateInfo;
        if (isState()) stateInfo = "state organization";
        else stateInfo = "private educational institution";

        String corporateInfo = "";
        if (!getCorporateGroup().equals("null")) corporateInfo = " of " + getCorporateGroup();

        String age;
        String ageFrom;
        String ageTo;
        if (getAgeLimit().equals("unlimited")) age = "of any age!! ";
        else if (getAgeLimit().startsWith("more")) age = "over the age of " + getAgeLimit().replace("more ", "") + ". ";
        else {
            String[] ages = getAgeLimit().split("-");
            ageFrom = ages[0];
            ageTo = ages[1];
            age = "between the ages of " + ageFrom + " and " + ageTo + ". ";
        }

        return "id" + getID() + " " +
                getName() +
                " (EIN: " + getEIN() + ")" +
                " is " + stateInfo +
                corporateInfo +
                " with a staff of " + getStuff() +
                " has been worked since " + getRegistrationDate().substring(0, 4) +
                ", can accept any applicant " + age +
                "This school located in " + getCountry() +
                " at " + getLocation() + ". " +
                "You can contact them by phone " + getConnection();
    }
}
