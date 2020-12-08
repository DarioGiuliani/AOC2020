package classes;

public class Interpreter {
    private int accumulator;
    private boolean infinite;

    public Interpreter(int accumulator, boolean infinite) {
        this.accumulator = accumulator;
        this.infinite = infinite;
    }

    public int getAccumulator() {
        return this.accumulator;
    }

    public void setAccumulator(int accumulator) {
        this.accumulator = accumulator;
    }

    public boolean isInfinite() {
        return this.infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }
}
