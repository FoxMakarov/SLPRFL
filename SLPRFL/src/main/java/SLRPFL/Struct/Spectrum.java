package SLRPFL.Struct;

public class Spectrum {

    public int testPassingExecutingNumber;

    public int testFailingExecutingNumber;

    public int testPassingNotExecutingNumber;

    public int testFailingNotExecutingNumber;

    public Spectrum(int testPassingExecutingNumber, int testFailingExecutingNumber,
                    int testPassingNotExecutingNumber, int testFailingNotExecutingNumber) {

        this.testFailingExecutingNumber = testFailingExecutingNumber;
        this.testFailingNotExecutingNumber = testFailingNotExecutingNumber;
        this.testPassingExecutingNumber = testPassingExecutingNumber;
        this.testPassingNotExecutingNumber = testPassingNotExecutingNumber;
    }

}
