package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Item {
    protected String content;
    protected Date due;
    protected Boolean status;
    protected Boolean urgency;

    // EFFECTS: creates an empty Item
    public Item() {
        this.content = null;
        this.due = null;
        this.status = false;
    }


    // EFFECTS: greets
    public abstract String greeting();

    // MODIFIES: this
    // EFFECTS: sets the content of the item
    public void setContent(String content) {
        this.content = content;
    }

    // REQUIRES: due > today
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

    // EFFECTS: get the content of the item
    public String getContent() {
        return content;
    }

    // EFFECTS: get the due date of the item
    public Date getDue() {
        return due;
    }

    // EFFECTS: get the completion status of the item (in boolean form)
    public Boolean getStatus() {
        return status;
    }

    // EFFECTS: get the urgency status of the item (in boolean form)
    public Boolean getUrg() {
        return urgency;
    }



    // EFFECTS: prints out the info of a to-do item in the form of a sentence
    public String printItem() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (status == true) {
            return (content + " is due on " + formatter.format((due)) + ". Completed!");
        } else {
            return (content + " is due on " + formatter.format((due)) + ". Not completed :(");
        }
    }
}
