package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Loadable {
    void loadFile() throws IOException, ParseException;
}
