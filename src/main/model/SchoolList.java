package model;

import java.util.ArrayList;
import java.util.List;


public class SchoolList extends Subject {
    public List<Item> schoolItems;

    // EFFECTS: constructs a SchoolList
    public SchoolList() {
        super();
        schoolItems = new ArrayList<>();
    }

    // MODIFIES: this, i
    // EFFECTS: adds i to schoolItems, if i is not already in schoolItems
    public void addItem(Item i) {
        if (!schoolItems.contains(i)) {
            schoolItems.add(i);
            i.addSchoolList(this);
            //notifyObservers(i);
        }
    }

    // MODIFIES: this, i
    // EFFECTS: removes i from schoolItems
    public void removeItem(Item i) {
        if (schoolItems.contains(i)) {
            schoolItems.remove(i);
            i.removeFromSchoolList();
        }
    }

    // EFFECTS: gets schoolItems
    public List<Item> getSchoolItems() {
        return schoolItems;
    }

    // EFFECTS: clears schoolItems
    public void clearSchoolItems() {
        schoolItems.clear();
    }


    // EFFECTS: makes two SchoolLists equal if they have same schoolItems
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SchoolList that = (SchoolList) o;
//        return Objects.equals(schoolItems, that.schoolItems);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(schoolItems);
//    }

}
