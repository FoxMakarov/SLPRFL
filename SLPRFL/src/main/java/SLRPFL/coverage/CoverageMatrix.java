package SLRPFL.coverage;

/*
* get coverage information from coverage.txt and testResult.txt file in PATH
* the coverage information's format for each line in coverage.txt is :
* "statementLine   testMethod1   testMethod2   testMethod3  ..."
* testMethod means the testMethod executing the statement
*
* the test information for each line in testResult.txt is :
* "testMethod boolInfo"
* means if the testMethod passed
* */


import SLRPFL.Struct.Location;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CoverageMatrix {

    private String FILEPATH = "";

    private String coveragePath = "";

    private String testResultPath = "";

    private Map<Location, Set<String> > coverageMatrix = new HashMap<>();

    private Map<String, Integer> lineExecutedByTest = new HashMap<>();

    private Map<String, Integer> failedTests = new HashMap<>();

    private Set<String> classExecutedByFailedTest = new HashSet<>();

    private Map<String, Integer> passedTests = new HashMap<>();

    public CoverageMatrix(String path) {
        FILEPATH = path;
        coveragePath = FILEPATH + "coverage.txt";
        testResultPath = FILEPATH + "testResult.txt";

        getTestResultInformation();
        getCoverageInformation();
    }

    private void getCoverageInformation() {

        try {
            File file = new File(coveragePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String lineStr;
            try {
                while ((lineStr = bufferedReader.readLine()) != null) {
                    String[] str = lineStr.split("\\s+");
                    boolean executedByFailedTests = false;

                    Location loc = new Location(str[0], Integer.parseInt(str[1]));
                    Set<String>currentExec = new HashSet<>();

                    for (int i = 2; i < str.length; i++) {
                        String testMethod = str[i];

                        if(failedTests.containsKey(testMethod)) {
                            executedByFailedTests = true;
                        }

                        currentExec.add(testMethod);

                        if(lineExecutedByTest.containsKey(testMethod)) {
                            int value = lineExecutedByTest.get(testMethod);
                            lineExecutedByTest.put(testMethod, value + 1);
                        } else {
                            lineExecutedByTest.put(testMethod, 1);
                        }
                    }

                    /*if(executedByFailedTests) {
                        classExecutedByFailedTest.add(str[0]);
                    }*/

                    if(!executedByFailedTests) {
                        continue;
                    }

                    if(!coverageMatrix.containsKey(loc)) {
                        coverageMatrix.put(loc, currentExec);
                    } else {
                        System.out.println("the same line!!!!");
                    }

                }

            }catch (IOException ie){
                 System.out.println("file read error!\n");
            }

        }
        catch (FileNotFoundException e)
        {

            System.out.println("file not existed!\n");
        }

    }

    private void getTestResultInformation() {
        try {
            File file = new File(testResultPath);
            FileInputStream fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            Integer passedNumber = new Integer(0);
            Integer failedNumber = new Integer(0);

            String lineStr;
            try {
                while ((lineStr = bufferedReader.readLine()) != null){
                    String[] str = lineStr.split("\\s+");
                    if (str[1].equals("1")) {
                        passedTests.put(str[0], passedNumber);
                        passedNumber = passedNumber + 1;
                    } else {
                        failedTests.put(str[0], failedNumber);
                        failedNumber = failedNumber + 1;
                    }

                }

            }catch (IOException ie){
                System.out.println("file read error!\n");
            }

        }
        catch (FileNotFoundException e)
        {

            System.out.println("file not existed!\n");
        }
    }

    public Map<Location, Set<String> > getCoverageMatrix() {
        return coverageMatrix;
    }

    public Map<String, Integer> getLineExecutedByTest() {
        return lineExecutedByTest;
    }

    public Map<String, Integer> getFailedTests() {
        return failedTests;
    }

    public Map<String, Integer> getPassedTests() {
        return passedTests;
    }

    public Set<String> getClassExecutedByFailedTest() {
        return classExecutedByFailedTest;
    }
}
