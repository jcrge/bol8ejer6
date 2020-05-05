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

    public void setOffsetWithColon(boolean offsetWithColon) {
        this.offsetWithColon = offsetWithColon;
    }

    public void setShowAscii(boolean showAscii) {
        this.showAscii = showAscii;
    }

    public void setByteChunkSize(int byteChunkSize) {
        this.byteChunkSize = byteChunkSize;
    }

    public void setOffsetWidth(int offsetWidth) {
        this.offsetWidth = offsetWidth;
    }

    public String getLine(int offset, byte[] bytes, int rightPadding) {
        return String.format(
            "%s %s %s",
            getOffsetCol(offset),
            getBytesCol(bytes, rightPadding),
            getAsciiCol(bytes));
    }

    private String getOffsetCol(int offset) {
        return String.format(
            "%" + (offsetWidth > 0 ? "0" + offsetWidth : "") + "x%s",
            offset,
            offsetWithColon ? ":" : "");
    }

    private String getBytesCol(byte[] bytes, int rightPadding) {
        String res = "";
        for (int i = 0; i < bytes.length; i++) {
            if (i > 0 && i % byteChunkSize == 0) {
                res += " ";
            }

            String addToRes = String.format("%02x", bytes[i]);
            if (i >= bytes.length - rightPadding) {
                addToRes = addToRes.replaceAll(".", " ");
            }

            res += addToRes;
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