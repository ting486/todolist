package model;

public class SubscriberData {
    // THIS IS FOR PRIVATE DATA CLASS DESIGN PATTERN, TO SATISFY DELIVERABLE 10
    // https://sourcemaking.com/design_patterns/private_class_data

    private String name;

    public SubscriberData(String name) {
        this.name = name;
    }

    // EFFECTS: gets name
    public String getName() {
        return name;
    }

}
