package model;

import java.io.IOException;
import java.text.ParseException;

public interface Loadable {
    void loadFile() throws IOException, ParseException;
}
