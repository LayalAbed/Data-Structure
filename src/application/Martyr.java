package application;
import java.util.Date;
public class Martyr implements Comparable<Martyr> {
    private String name;
    private char gender;
    private Date dateOfMartyr;
    private int age;
    private String eventLocation;

    public Martyr(String name, int age, String eventLocation, Date dateOfMartyr, char gender) {
        this.name = name;
        this.gender = gender;
        this.dateOfMartyr = dateOfMartyr;
        this.age = age;
        this.eventLocation = eventLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDateOfMartyr() {
        return dateOfMartyr;
    }

    public void setDateOfMartyr(Date dateOfMartyr) {
        this.dateOfMartyr = dateOfMartyr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public int compareTo(Martyr other) {
        int nameComparison = this.name.compareTo(other.name);
        return nameComparison != 0 ? nameComparison : Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return "Martyr [name=" + name + ", gender=" + gender + ", dateOfMartyr=" + dateOfMartyr + ", age=" + age
                + ", eventLocation=" + eventLocation + "]";
    }
}

