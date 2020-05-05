package app;

import lineformatter.*;
import parsedargs.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Si hiciera un parseador de argumentos serio (que imagino que ya los
        // habrá por ahí, de todos modos), lo haría para que en vez de acceder
        // a los atributos por propiedades específicas se pudiera declarar
        // nombres de propiedades con un tipo asociado, de modo que en vez de:
        //   parsedArgs.byteChunkSize  /* tipo int */
        // Se hiciese por ejemplo:
        //   parsedArgs.getParam("chunkSize")  /* tipo int */
        // Tras haber declarado en parámetro tal vez como:
        //   parsedArgs.setParam("s", "chunkSize", "int") /* `-s 3` o `--chunkSize 3` */
        // Pero esto ya se sale mucho de lo que se pide en el ejercicio.
        ParsedArgs parsedArgs = null;

        try {
            parsedArgs = new ParsedArgs(args);
        } catch (InvalidArgumentsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            LineFormatter lineFormatter = new LineFormatter();
            lineFormatter.setByteChunkSize(parsedArgs.byteChunkSize);
            lineFormatter.setOffsetWithColon(parsedArgs.offsetWithColon);
            lineFormatter.setShowAscii(parsedArgs.showAscii);
            lineFormatter.setOffsetWidth(parsedArgs.offsetWidth);

            int offset = 0;
            byte[] buffer = new byte[parsedArgs.bytesPerLine];
            int bytesRead;

            do {
                bytesRead = parsedArgs.inputStream.readNBytes(buffer, 0, parsedArgs.bytesPerLine);
                if (bytesRead > 0) {
                    System.out.println(lineFormatter.getLine(offset, buffer, buffer.length - bytesRead));
                }
                offset += bytesRead;
            } while (bytesRead > 0);
        } finally {
            parsedArgs.inputStream.close();
        }
    }
}