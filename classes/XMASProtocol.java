package classes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XMASProtocol {
    private List<Long> preamble;
    private Long cypher;

    public void setPreamble(List<Long> preamble) {
        this.preamble = preamble;
    }

    public List<Long> getPreamble() {
        return this.preamble;
    }

    public void setCypher(Long cypher) {
        this.cypher = cypher;
    }

    public Long getCypher() {
        return this.cypher;
    }

    public boolean isValid() {
        Set<Long> validSums = new HashSet<>();

        for (int i = 0; i < this.preamble.size(); i++) {
            for(int j = this.preamble.size() - 1; j > i; j--) {
                validSums.add(this.preamble.get(i) + this.preamble.get(j));
            }
        }

        return validSums.contains(cypher);
    }
}
