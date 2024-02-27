import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String name;
    private String address;
    private String category;
    private LocalDateTime dateTime;
    private String description;

    public Event(String name, String address, String category, LocalDateTime dateTime, String description) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
