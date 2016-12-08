package menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * a class that reads a specific line from a file.
 */
public class LineNumberReader extends BufferedReader {
    private int lineNumber = 1;

    /**
     * constructor.
     *
     * @param in the io reader.
     * @param sz the size of the file.
     */
    public LineNumberReader(Reader in, int sz) {
        super(in, sz);
    }

    /**
     * constructor.
     *
     * @param in the io reader.
     */
    public LineNumberReader(Reader in) {
        super(in);
    }


    @Override
    public String readLine() throws IOException {
        lineNumber++;
        return super.readLine();
    }

    /**
     * getter.
     *
     * @return the line number we are on
     */
    public int getLineNumber() {
        return this.lineNumber;
    }
}
