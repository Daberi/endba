
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The WordFreq class processes input text files, creates term-frequency and
 * outputs them to a file.
 *
 * @author redae
 */
public class WordFreq {

    private Map<String, Integer> map;
    Charset charset = Charset.forName("UTF-8");

    public WordFreq() {
        map = new HashMap<>();
    }

    /**
     * Reads text files and creates vocabulary with frequency of each word.
     *
     * @param files array of filenames
     * @param delimiter used to split text to tokens
     */
    public void readFiles(String[] files, String delimiter) {
        for (String f : files) {
            if (Files.exists(Paths.get(f))) {
                try (BufferedReader br = Files.newBufferedReader(Paths.get(f), charset)) {
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        String[] tokens = line.split(delimiter);
                        for (String token : tokens) {
                            if (!token.isEmpty()) {
                                if (map.containsKey(token)) {
                                    map.put(token, map.get(token) + 1);
                                } else {
                                    map.put(token, 1);
                                }
                            }
                        }
                    }
                } catch (IOException ex) {
                    System.err.format("IOException: %s%n", ex);
                }
            } else {
                System.out.println("File not found.");
                return;
            }
        }
    }

    /**
     * Writes the contents of hashmap to a file.
     *
     * @param outFile name of output file
     */
    public void writeMap(String outFile) {

        Path path = Paths.get(outFile);

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();

            try (BufferedWriter bw = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)) {
                bw.write(entry.getKey() + "\t" + entry.getValue());
                bw.newLine();
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }
    }
}
