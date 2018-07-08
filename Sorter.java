
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

/**
 * The Sorter class sorts Geez text in ascending or descending order.
 *
 * @author redae
 */
public class Sorter {

    private final String[] orderdChars = {
        "ሀ", "ሁ", "ሂ", "ሃ", "ሄ", "ህ", "ሆ",
        "ለ", "ሉ", "ሊ", "ላ", "ሌ", "ል", "ሎ",
        "ሐ", "ሑ", "ሒ", "ሓ", "ሔ", "ሕ", "ሖ",
        "መ", "ሙ", "ሚ", "ማ", "ሜ", "ም", "ሞ",
        "ረ", "ሩ", "ሪ", "ራ", "ሬ", "ር", "ሮ",
        "ሰ", "ሱ", "ሲ", "ሳ", "ሴ", "ስ", "ሶ",
        "ሸ", "ሹ", "ሺ", "ሻ", "ሼ", "ሽ", "ሾ",
        "ቀ", "ቁ", "ቂ", "ቃ", "ቄ", "ቅ", "ቆ",
        "ቐ", "ቑ", "ቒ", "ቓ", "ቔ", "ቕ", "ቖ",
        "ቈ", "ቊ", "ቋ", "ቌ", "ቍ",
        "ቘ", "ቚ", "ቛ", "ቜ", "ቝ",
        "በ", "ቡ", "ቢ", "ባ", "ቤ", "ብ", "ቦ",
        "ቨ", "ቩ", "ቪ", "ቫ", "ቬ", "ቭ", "ቮ",
        "ተ", "ቱ", "ቲ", "ታ", "ቴ", "ት", "ቶ",
        "ቸ", "ቹ", "ቺ", "ቻ", "ቼ", "ች", "ቾ",
        "ነ", "ኑ", "ኒ", "ና", "ኔ", "ን", "ኖ",
        "ኘ", "ኙ", "ኚ", "ኛ", "ኜ", "ኝ", "ኞ",
        "አ", "ኡ", "ኢ", "ኣ", "ኤ", "እ", "ኦ",
        "ከ", "ኩ", "ኪ", "ካ", "ኬ", "ክ", "ኮ",
        "ኰ", "ኲ", "ኳ", "ኴ", "ኵ",
        "ኸ", "ኹ", "ኺ", "ኻ", "ኼ", "ኽ", "ኾ",
        "ዀ", "ዂ", "ዃ", "ዄ", "ዅ",
        "ወ", "ዉ", "ዊ", "ዋ", "ዌ", "ው", "ዎ",
        "ዐ", "ዑ", "ዒ", "ዓ", "ዔ", "ዕ", "ዖ",
        "ዘ", "ዙ", "ዚ", "ዛ", "ዜ", "ዝ", "ዞ",
        "ዠ", "ዡ", "ዢ", "ዣ", "ዤ", "ዥ", "ዦ",
        "የ", "ዩ", "ዪ", "ያ", "ዬ", "ይ", "ዮ",
        "ደ", "ዱ", "ዲ", "ዳ", "ዴ", "ድ", "ዶ",
        "ጀ", "ጁ", "ጂ", "ጃ", "ጄ", "ጅ", "ጆ",
        "ገ", "ጉ", "ጊ", "ጋ", "ጌ", "ግ", "ጎ",
        "ጐ", "ጒ", "ጓ", "ጔ", "ጕ",
        "ጘ", "ጙ", "ጚ", "ጛ", "ጜ", "ጝ", "ጞ",
        "ⶓ", "ⶔ", "ጟ", "ⶕ", "ⶖ",
        "ጠ", "ጡ", "ጢ", "ጣ", "ጤ", "ጥ", "ጦ",
        "ጨ", "ጩ", "ጪ", "ጫ", "ጬ", "ጭ", "ጮ",
        "ፈ", "ፉ", "ፊ", "ፋ", "ፌ", "ፍ", "ፎ",
        "ፐ", "ፑ", "ፒ", "ፓ", "ፔ", "ፕ", "ፖ"
    };

    public enum sortOrders {
        ASC, DESC
    };

    public Sorter() {

    }

    /**
     * Converts a string to integer string.
     *
     * @param str string of words
     * @return integer representation of the string
     */
    public String stringToInteger(String str) {
        String ints = "";
        List<String> list;

        for (int i = 0; i < str.length(); i++) {
            list = Arrays.asList(orderdChars);
            int index = list.indexOf(str.charAt(i));
            if (index != -1) {
                ints += index;
            } else {
                ints += str.charAt(i);
            }
        }

        return ints;
    }

    /**
     * Sorts strings in ascending or descending order.
     *
     * @param strs list of strings to be sorted
     * @param sortOrder ASC or DESC
     * @return list of strings sorted in sortOrder
     */
    public List<String> sort(List<String> strs, String sortOrder) {
        MultiValuedMap<String, String> mMap = new ArrayListValuedHashMap<>();
        List<String> values = new ArrayList<>();

        strs.forEach((s) -> {
            mMap.put(stringToInteger(s), s);
        });

        List<String> keys = new ArrayList<>(mMap.keySet());

        if (sortOrder.equalsIgnoreCase(sortOrders.ASC.name())) {
            Collections.sort(keys);

            keys.forEach((k) -> {
                values.addAll(mMap.get(k));
            });
        } else if (sortOrder.equalsIgnoreCase(sortOrders.DESC.name())) {
            Collections.sort(keys, Collections.reverseOrder());

            keys.forEach((k) -> {
                values.addAll(mMap.get(k));
            });
        } else {
            keys.forEach((k) -> {
                values.addAll(mMap.get(k));
            });
        }

        return values;
    }
}
