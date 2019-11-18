package model;

public class RegularItem extends Item {

    public RegularItem() {
        super();
        this.urgency = false;
        //this.schoolList = null;
    }

    // EFFECTS: greets
    @Override
    public String greeting() {
        return  "This is regular";
    }

}