package parsedargs;

import java.io.*;

public class ParsedArgs {
    public int byteChunkSize;
    public int offsetWidth;
    public int bytesPerLine;
    public boolean offsetWithColon;
    public boolean showAscii;
    public boolean showOffset;
    public InputStream inputStream;

    public ParsedArgs(String[] args) throws InvalidArgumentsException, IOException {
        setDefaults();

        try {
            while (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "--chunk-size":
                    case "-s":
                        try {
                            byteChunkSize = Integer.parseInt(args[1]);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            throw new InvalidArgumentsException("--chunk-size", "número inválido");
                        }

                        args = removeFromBeginning(args, 2);
                    break;

                    case "--offset-width":
                    case "-o":
                        try {
                            offsetWidth = Integer.parseInt(args[1]);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            throw new InvalidArgumentsException("--offset-width", "número inválido");
                        }

                        args = removeFromBeginning(args, 2);
                    break;

                    case "--bytes-per-line":
                    case "-l":
                        try {
                            bytesPerLine = Integer.parseInt(args[1]);
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            throw new InvalidArgumentsException("--bytes-per-line", "número inválido");
                        }

                        args = removeFromBeginning(args, 2);
                    break;

                    case "--colon":
                    case "-c":
                        offsetWithColon = true;
                        args = removeFromBeginning(args, 1);
                    break;

                    case "--show-offset":
                    case "-x":
                        showOffset = true;
                        args = removeFromBeginning(args, 1);
                    break;

                    case "--ascii":
                    case "-a":
                        showAscii = true;
                        args = removeFromBeginning(args, 1);
                    break;

                    case "--input-file":
                    case "-f":
                        try {
                            inputStream = new FileInputStream(args[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new InvalidArgumentsException("--input-file", "archivo inválido");
                        }

                        args = removeFromBeginning(args, 2);
                    break;
                }
            }
        } catch (InvalidArgumentsException e) {
            if (inputStream != null) {
                inputStream.close();
            }

            inputStream = null;
            throw e;
        }
    }

    private void setDefaults() {
        byteChunkSize = 1;
        offsetWidth = 8;
        bytesPerLine = 16;
        offsetWithColon = false;
        showAscii = false;
        showOffset = false;
        inputStream = System.in;
    }

    private static String[] removeFromBeginning(String[] source, int amount) {
        String[] dest = new String[source.length - amount];
        for (int destPos = 0; destPos < dest.length; destPos++) {
            dest[destPos] = source[destPos + amount];
        }

        return dest;
    }
}