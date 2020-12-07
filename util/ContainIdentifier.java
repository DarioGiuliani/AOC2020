package util;

import java.util.List;
import java.util.Set;

public class ContainIdentifier {
    public static boolean containsOneIdentifier(String input, List<String> identifiers) {
        for (String identifier : identifiers) {
            if(input.contains(identifier)){
                return true;
            }
        }

        return false;
    }

    public static boolean containsOneIdentifier(String input, Set<String> identifiers) {
        for (String identifier : identifiers) {
            if(input.contains(identifier)){
                return true;
            }
        }

        return false;
    }

    public static boolean containsAllIdentifiers(String input, List<String> identifiers) {
        for (String identifier : identifiers) {
            if(!input.contains(identifier)){
                return false;
            }
        }

        return true;
    }
}
