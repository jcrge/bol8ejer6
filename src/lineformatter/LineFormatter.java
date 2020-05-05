package lineformatter;

public class LineFormatter {
    private boolean offsetWithColon;
    private boolean showAscii;
    private int byteChunkSize;
    private int offsetWidth;

    public LineFormatter() {
        offsetWithColon = true;
        showAscii = true;
        byteChunkSize = 4;
        offsetWidth = 0;
    }

    public String getLine(int offset, byte[] bytes) {
        return String.format(
            "%s %s %s",
            getOffsetCol(offset),
            getBytesCol(bytes),
            getAsciiCol(bytes));
    }

    private String getOffsetCol(int offset) {
        return String.format(
            "%" + (offsetWidth > 0 ? "0" + offsetWidth : "") + "x%s",
            offset,
            offsetWithColon ? ":" : "");
    }

    private String getBytesCol(byte[] bytes) {
        String res = "";
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0 && i % byteChunkSize == 0) {
                res += " ";
            }

            res += String.format("%02x", bytes[i]);
        }

        return res;
    }

    private String getAsciiCol(byte[] bytes) {
        if (!showAscii) {
            return "";
        }

        String res = "";
        for (int i = 0; i < bytes.length; i++) {
            char currByte = (char)bytes[i];
            res += String.format("%c", isPrintable(currByte) ? currByte : '.');
        }

        return res;
    }

    private static boolean isPrintable(char what) {
        return what >= 32 && what <= 127;
    }
}