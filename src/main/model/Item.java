package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Item {
    protected String content;
    protected Date due;
    protected boolean status;
    protected boolean urgency;
    protected SchoolList schoolList;
    public static final String FORMATTER_PATTERN = "dd/MM/yyyy";
    public SimpleDateFormat formatter = new SimpleDateFormat(FORMATTER_PATTERN);


    public Item() {
        this.content = null;
        this.due = null;
        this.status = false;
        this.schoolList = null;
    }


    // EFFECTS: greets
    public abstract String greeting();

    // MODIFIES: this
    // EFFECTS: sets the content of the item
    public void setContent(String content) {
        this.content = content;
    }

    // MODIFIES: this
    // EFFECTS: sets the due date of the item
    public void setDue(Date due) {
        this.due = due;
    }

    // MODIFIES: this
    // EFFECTS: sets the completion status of the entry
    public void setStatus(Boolean status) {
        this.status = status;
    }

    // MODIFIES: this
    // EFFECTS: sets the content, due and completion status of the item
    public void setThis(String content, Date due, Boolean status) {
        setContent(content);
        setDue(due);
        setStatus(status);
    }

    // EFFECTS: gets the content
    public String getContent() {
        return content;
    }

    // EFFECTS: gets the due date
    public Date getDue() {
        return due;
    }

    // EFFECTS: gets the completion status
    public boolean getStatus() {
        return status;
    }

    // EFFECTS: gets the urgency status
    public boolean getUrg() {
        return urgency;
    }

    // EFFECTS: gets schoolList
    public SchoolList getSchoolList() {
        return schoolList;
    }

    // EFFECTS: prints out the info of a to-do item in the form of a sentence
    public String printItem() {
        if (status) {
            return (content + " is due on " + formatter.format((due)) + ". Completed!");
        } else {
            return (content + " is due on " + formatter.format((due)) + ". Not completed :(");
        }
    }

    // MODIFIES: this, sl
    // EFFECTS: adds sl to schoolList
    public void addSchoolList(SchoolList sl) {
        if (schoolList != sl) {
            schoolList = sl;
            sl.addItem(this);
            sl.notifyObservers(this);
        }
    }

    // MODIFIES: this, sl
    // EFFECTS: empties schoolList
    public void removeFromSchoolList() {
        if (getSchoolList() != null) {
            schoolList.removeItem(this);
            schoolList = null;
        }
    }

    // EFFECTS: returns true if getSchoolList() is not null
    public boolean isInSchool() {
        return getSchoolList() != null;
    }


    // EFFECTS: returns true if two Items have same content, due, status and urgency
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return content.equals(item.content)
                && due.equals(item.due)
                && Objects.equals(status, item.status)
                && Objects.equals(urgency, item.urgency);
    }

    // EFFECTS: returns an Item's hashCode based on its content, due, status and urgency
    @Override
    public int hashCode() {
        return Objects.hash(content, due, status, urgency);
    }

}
