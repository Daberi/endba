
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

public class WordFreq {
    private Map<String, Integer> map;    
    Charset charset = Charset.forName("UTF-8");
        
    public WordFreq(){
        map = new HashMap<>();
    }
    
    public void readFiles(String[] files, String delimiter){        
        for (String f: files){
            if(Files.exists(Paths.get(f))){
                try(BufferedReader br = Files.newBufferedReader(Paths.get(f), charset)){
                    String line = null;
                    while ((line = br.readLine()) != null){
                        String[] tokens = line.split(delimiter);
                        for (String token: tokens){
                            if (!token.isEmpty()){
                                if(map.containsKey(token)){
                                    map.put(token, map.get(token) + 1);
                                } else {
                                    map.put(token, 1);
                                }
                            }
                        }
                    }
                } catch(IOException ex){
                    System.err.format("IOException: %s%n", ex);
                }
            } else {
                System.out.println("File not found.");
                return;
            }
        }
        
    }
    
    public void writeMap(String outFile){
        Path path = Paths.get(outFile);
        
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            
            try(BufferedWriter bw = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)){
                bw.write(entry.getKey() + "\t" + entry.getValue());
                bw.newLine();
            } catch(IOException ex){
                System.err.format("IOException: %s%n", ex);
            }
        }
    }
}
