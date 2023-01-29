package com.company.classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private static final String FILE_NAME = "simulation.txt";
    private static BufferedWriter WRITER = null;

    static {
        try {
            WRITER = new BufferedWriter(new FileWriter(FILE_NAME, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeIntoFile(String s) throws IOException {
        WRITER.write(s + "\n");
        WRITER.flush();
    }

    public static void close() throws IOException {
        WRITER.close();
    }
}
