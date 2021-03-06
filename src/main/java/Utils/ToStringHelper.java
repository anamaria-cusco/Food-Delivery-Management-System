package Utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToStringHelper {

    private String separator;
    private String arrow;

    public ToStringHelper(String separator, String arrow) {
        this.separator = separator;
        this.arrow = arrow;
    }

    public String toString(List<?> l) {
        StringBuilder sb = new StringBuilder("");
        String sep = "";
        for (Object object : l) {
            sb.append(sep).append(object.toString());
            sep = separator;
        }
        return sb.toString();
    }

    public String toString(Map<?,?> m) {
        StringBuilder sb = new StringBuilder("");
        String sep = "";
        for (Object object : m.keySet()) {
            sb.append(sep)
                    .append(object.toString())
                    .append(arrow)
                    .append(m.get(object).toString())
                    .append("\n");
            sep = separator;
        }
        return sb.toString();
    }


}