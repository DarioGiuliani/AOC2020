package util;

import java.util.ArrayList;
import java.util.List;

public class Appender {
    public static List<String> appendInputs(List<String> input, String delimiter) {
        List<String> appendedInputs = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (String string : input) {
            if(string.isEmpty()){   
                appendedInputs.add(builder.toString().trim());
                builder.setLength(0);
            }
            builder.append(delimiter);
            builder.append(string);
        }
        appendedInputs.add(builder.toString().trim());

        return appendedInputs;
    }
}
