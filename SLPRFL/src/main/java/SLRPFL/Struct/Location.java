package SLRPFL.Struct;

public class Location {

    private String className;

    private Integer lineNumber;

    public Location(String className, Integer lineNumber) {
        this.className = className;
        this.lineNumber = lineNumber;
    }

    public String getClassName() {
        return className;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public String print() {
        return className + "." + lineNumber;
    }

}
