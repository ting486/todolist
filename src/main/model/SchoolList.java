package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SchoolList {
    public List<Item> schoolItems;

    public SchoolList() {
        schoolItems = new ArrayList<>();
    }


    public void addItem(Item i) {
        if (!schoolItems.contains(i)) {
            schoolItems.add(i);
            i.addSchoolList(this);
        }
    }

    public void removeItem(Item i) {
        if (schoolItems.contains(i)) {
            schoolItems.remove(i);
            i.removeFromSchoolList();
        }
    }

    public List<Item> getSchoolItems() {
        return schoolItems;
    }

//    public void displaySchoolItems() {
//        for (Item i: schoolItems) {
//            i.printItem();
//        }
//        schoolItems.forEach(item -> item.printItem());
//    }

    public void clearSchoolItems() {
        schoolItems.clear();
    }

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
