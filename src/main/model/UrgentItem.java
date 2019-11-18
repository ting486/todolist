package model;

public class UrgentItem extends Item {

    public UrgentItem() {
        super();
        this.urgency = true;
    }

    // EFFECTS: greets
    @Override
    public String greeting() {
        return ("This is urgent");
    }

}
