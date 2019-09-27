package model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Entry {
    private String content;
    private Date due;
    private Boolean completion;
    //private Category category;

    // constructor
    public Entry() {
        this.content = null;
        this.due = null;
        this.completion = false;
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
    public void setCompletion(Boolean completion) {
        this.completion = completion;
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

    /***
     public void getDue(Date due) {
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     System.out.println("It is due on " + formatter.format(due));
     }
     ***/

    // EFFECTS: get the completion status of the entry (in boolean form)
    public Boolean getCompletion() {
        return completion;
    }

    /*****
     // EFFECTS: describes the completion status in words
     public String completionStatus(Boolean completion) {
     if (completion == true) {
     return ("Completed!");
     } else {
     return ("Not completed :(");
     }
     }
     ****/


    // EFFECTS: prints out the info of a to-do entry in the form of a sentence
    public String printEntry() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (completion == true) {
            return (content + " is due on " + formatter.format((due)) + ". Completed!");
        } else {
            return (content + " is due on " + formatter.format((due)) + ". Not completed :(");
        }
    }



    /*
     public static void main(String[] args) throws ParseException {

     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     //String strDue = formatter.format(new Date());
     //System.out.println("strDue: " + strDue);
     /**
     try {
     Date datee = formatter.parse("20/05/2020");
     System.out.println("Try date: " + datee);
     } catch (ParseException e) {
     e.printStackTrace();
     } **/

    /*
     Entry cpsc = new Entry();

     // a sample entry
     cpsc.setContent("CPSC project deliverable 1");
     //cpsc.setDue(new Date(00, 00, 21));
     cpsc.setDue(formatter.parse("13/11/2019"));
     cpsc.setCompletion(false);

     //System.out.println("test: " + formatter.format(formatter.parse("20/01/2021")));

     System.out.println(cpsc.content + " is due on "
     + formatter.format((cpsc.due)) + " (dd/MM/yyyy). "
     + cpsc.completionStatus(cpsc.completion));    //content, due date & completion status in one sent.
     }

     */



}