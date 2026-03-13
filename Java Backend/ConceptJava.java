import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConceptJava {

    public static void main(String[] args) {
        List<Map<String, String>> list = new ArrayList<>();
        list.add(new HashMap<String, String>() {{
            put("next", "data");
        }
        });
    }
}