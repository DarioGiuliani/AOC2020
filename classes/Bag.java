package classes;

import java.util.HashMap;
import java.util.Map.Entry;

public class Bag {
    private final String name;

    private final HashMap<Bag, Integer> bags = new HashMap<>();

    public Bag(String name) {
        this.name = name;
    }

    public void addBags(Bag bag, int count) {
        this.bags.put(bag, count);
    }

    public boolean containsBag(String name) {
        if (this.name.equals(name)) {
            return false;
        }

        for (Bag bag : bags.keySet()) {
            if (bag.name.equals(name) || bag.containsBag(name)) {
                return true;
            }
        }
            
        return false;
    }

    public int countBags() {
        int total = 0;
        for (Entry<Bag, Integer> entry : this.bags.entrySet()) {
            total += entry.getValue() + entry.getValue() * entry.getKey().countBags();
        }

        return total;
    }
}
