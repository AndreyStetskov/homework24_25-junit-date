package organization.educational;

import organization.Organization;

import java.util.Objects;

public class Educational extends Organization {
    private String ageLimit;


    public Educational(String EIN, String name, String ageLimit, String corporateGroup, boolean isState, int stuff, String registrationDate, String location, String country, String connection, boolean isCurrent) {
        super(EIN, name, corporateGroup, stuff, isState, registrationDate, location, country, connection, isCurrent);
        this.ageLimit = ageLimit;
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
        Educational that = (Educational) o;
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

        return getName() +
                " (EIN: " + getEIN() + ")" +
                " is " + stateInfo +
                corporateInfo +
                "with a staff of " + getStuff() +
                " has been worked since " + getRegistrationDate().substring(0, 4) +
                ", can accept any applicant " + age +
                "This school located in " + getCountry() +
                " at " + getLocation() + ". " +
                "You can contact them by phone " + getConnection();
    }
}
