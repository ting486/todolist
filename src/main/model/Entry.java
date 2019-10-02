package main.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Entry {
    private String content;
    private Date due;
    private Boolean status;
    //private Category category;

    // constructor
    public Entry() {
        this.content = null;
        this.due = null;
        this.status = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the content of the entry
    public void setContent(String content) {
        this.content = content;
        //System.out.println(content + " is due soon");
    }

    // REQUIRES: due > today
    // MODIFIES: this
    // EFFECTS: sets the due date of the entry
    public void setDue(Date due) {
        this.due = due;
        //System.out.println("due on " + due);
    }

    // MODIFIES: this
    // EFFECTS: sets the completion status of the entry
    public void setStatus(Boolean status) {
        this.status = status;
        /*
         if (completion == true) {
         System.out.println("Completed!");
         } else {
         System.out.println("Not completed :(");
         }
         */
    }


    // EFFECTS: get the content of the entry
    public String getContent() {
        return content;
    }

    // EFFECTS: get the due date of the entry
    public Date getDue() {
        return due;
    }

    /*
     public void getDue(Date due) {
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     System.out.println("It is due on " + formatter.format(due));
     }
     ***/

    // EFFECTS: get the completion status of the entry (in boolean form)
    public Boolean getStatus() {
        return status;
    }


    // EFFECTS: describes the completion status in words
    public String statusStr(Boolean status) {
        if (status == true) {
            return ("Completed!");
        } else {
            return ("Not completed :(");
        }
    }



    // EFFECTS: prints out the info of a to-do entry in the form of a sentence
    public String printEntry() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (status == true) {
            return (content + " is due on " + formatter.format((due)) + ". Completed!");
        } else {
            return (content + " is due on " + formatter.format((due)) + ". Not completed :(");
        }
    }
}