package model;

import ui.ToDoList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Item {
    protected String content;
    protected Date due;
    protected boolean status;
    protected boolean urgency;
    protected SchoolList schoolList;
//    public ToDoList toDoListIsIn;
    //public List<Item> inDone;

    // EFFECTS: creates an empty Item
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
    public boolean getStatus() {
        return status;
    }

    // EFFECTS: get the urgency status of the item (in boolean form)
    public boolean getUrg() {
        return urgency;
    }

    public SchoolList getSchoolList() {
        return schoolList;
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



    public void addSchoolList(SchoolList sl) {
        if (schoolList != sl) {
            schoolList = sl;
            sl.addItem(this);
        }
    }

    public void removeFromSchoolList() {
        if (getSchoolList() != null) {
            schoolList.removeItem(this);
            schoolList = null;
        }
    }


    public boolean isInSchool() {
        if (getSchoolList() != null) {
            return true;
        } else {
            return false;
        }
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(content, due, status, urgency);
    }

}
