package main.model;

import java.io.IOException;

public interface Saveable {
    void saveToFile() throws IOException;
}
