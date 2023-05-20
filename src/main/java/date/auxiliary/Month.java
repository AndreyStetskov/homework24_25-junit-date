package date.auxiliary;

public enum Month {
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    private final int day;

    Month(int day) {
        this.day = day;
    }

    public static int dayInMonth(int month, int year) {

        if (isLeap(year) && Month.values()[month - 1] == Month.FEBRUARY) return Month.values()[month - 1].getDay() + 1;
        else return Month.values()[month - 1].getDay();
    }

    public static boolean isLeap(int year) {

        if (year % 4 == 0 && year != 100 || year % 400 == 0) return true;
        else return false;
    }

    public int getDay() {
        return day;
    }
}
