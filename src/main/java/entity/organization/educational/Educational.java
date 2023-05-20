package entity.organization.educational;

import entity.organization.Organization;

import java.util.Objects;

public class Educational extends Organization {
    private String ageLimit;


    public Educational(int ID, String EIN, String name, String corporateGroup, boolean isState, int stuff, String registrationDate, String location, String country, String connection, boolean isCurrent, String ageLimit) {
        super(ID, EIN, name, corporateGroup, isState, stuff, registrationDate, location, country, connection, isCurrent);
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
        return "Educational{" +
                "ageLimit='" + ageLimit + '\'' +
                '}';
    }
}
