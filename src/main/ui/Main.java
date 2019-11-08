package ui;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        ToDoList runThisList = new ToDoList();
        runThisList.run();
    }
}

/****


low cohesion:
- I believe I don't have much issue with low cohesion. Even though my ToDoList is quite long, all methods operate
  on toDoMap & doneMap, the two central field of this class. I believe all classes in model perform single duty.

high coupling:
- SimpleDateFormat is instantiated in multiple locations; if I change the pattern, moderate or even semantic coupling
  can happen
- in ToDoList, operationDisplayTodo() and operationDisplayDone() have almost the same code; as well as loadToToDo()
  and loadToDone(); and the two main blocks of codes in SaveToFile()

changes made to reduce semantic coupling and/or low cohesion:
- created a FORMATTER_PATTERN in Item s.t. all date format follow the same pattern thru-out the program
- operationDisplayMap(Map<String, Item> map) was used to reduce high coupling in operationDisplayTodo()
  and operationDisplayDone()
- putIntoMap(...) was used to reduce high coupling in loadToDone()

 note: SaveSample and LoadSample are here simply to help test saveToFile() and LoadFile() methods in ToDoList class,
       thus no real responsibilities in terms of making main() execute properly

 ******/
