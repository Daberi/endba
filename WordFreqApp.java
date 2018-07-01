
import java.io.File;
import java.io.IOException;

public class WordFreqApp {

    public static void main(String[] args) {

        // delimiters
        String regex = "[\\s…()\\u1361-\\u1367\\u0022\\u201C\\u201D]";
        String outFilePath = "docs" + File.separator + "out.txt";
        WordFreq wf = new WordFreq();

        if (args.length != 0) {
            // Create the output file
            try {
                File outFile = new File(outFilePath);
                outFile.createNewFile();
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
            wf.readFiles(args, regex);
        } else {
            System.err.println("Please provide filenames as commandline args.");
            System.exit(0);
        }

        wf.writeMap(outFilePath);
    }
}
