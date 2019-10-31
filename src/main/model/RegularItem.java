package model;


import java.util.Objects;

public class RegularItem extends Item {
    private SchoolList schoolList;

    // EFFECTS: creates an empty RegularItem
    public RegularItem() {
        super();
        this.urgency = false;
        this.schoolList = null;
    }


    // EFFECTS: greets
    @Override
    public String greeting() {
        return  "This is regular";
    }





//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Item item = (Item) o;
//        return content.equals(item.content)
//                && due.equals(item.due)
//                && Objects.equals(status, item.status)
//                && Objects.equals(urgency, item.urgency);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(content, due, status, urgency);
//    }





//    public void addSchoolList(SchoolList sl) {
//        if (schoolList != sl) {
//            schoolList = sl;
//            sl.addItem(this);
//        }
//    }
//
//    public void removeFromSchoolList() {
//        if (getSchoolList() != null) {
//            schoolList.removeItem(this);
//            schoolList = null;
//        }
//    }
//
//    public SchoolList getSchoolList() {
//        return schoolList;
//    }




    /********
    // MODIFIES: this
    // EFFECTS: sets the content of the item
    @Override
    public void setContent(String content) {
        super.setContent(content);
    }


    // MODIFIES: this
    // EFFECTS: sets the due date of the item
    @Override
    public void setDue(Date due) {
        super.setDue(due);
    }

    // MODIFIES: this
    // EFFECTS: sets the completion status of the item
    @Override
    public void setStatus(Boolean status) {
        super.setStatus(status);
    }

    // MODIFIES: this
    // EFFECTS: sets the content, due and completion status of the item
    @Override
    public void setThis(String content, Date due, Boolean status) {
        super.setThis(content, due, status);
    }

    // EFFECTS: get the content of the item
    @Override
    public String getContent() {
        return super.getContent();
    }

    // EFFECTS: get the due date of the item
    @Override
    public Date getDue() {
        return super.getDue();
    }

    // EFFECTS: get the completion status of the item (in boolean form)
    @Override
    public Boolean getStatus() {
        return super.getStatus();
    }

    // EFFECTS: get the urgency status of the entry (in boolean form)
    @Override
    public Boolean getUrg() {
        return super.getUrg();
    }

    // EFFECTS: prints out the info of a to-do item in the form of a sentence
    @Override
    public String printItem() {
        return super.printItem();
    }
    ***********/
}