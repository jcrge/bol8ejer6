package app;

import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = null;
        try {
            if (args.length > 0) {
                inputStream = new FileInputStream(args[0]);
            } else {
                inputStream = System.in;
            }
        } finally {
            inputStream.close();
        }
    }
}