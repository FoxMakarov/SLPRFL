package SLRPFL.formulas;

public interface Formula {

    public double compute(int nPassingNotExecuting, int nFailingNotExecuting, int nPassingExecuting,
                          int nFailingExecuting);

    public double compute(Double nPassingNotExecuting, Double nFailingNotExecuting, Double nPassingExecuting,
                          Double nFailingExecuting);
}
