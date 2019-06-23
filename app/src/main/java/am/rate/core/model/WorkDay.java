package am.rate.core.model;

public class WorkDay {
    private String days;
    private String hours;

    public String getDays() {
        if (days.equals("1-5")){
            return "Երկուշաբթի ֊ Ուրբաթ";
        } else if (days.equals("1-6")){
            return "Երկուշաբթի ֊ Շաբաթ";
        } else if (days.equals("6")){
            return "Շաբաթ";
        }
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
