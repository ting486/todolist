package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Saveable {
    void saveToFile() throws IOException;
}
