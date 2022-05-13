package SLRPFL;

import SLRPFL.PDG.PDGAnalysis;
import SLRPFL.Struct.Spectrum;
import SLRPFL.coverage.CoverageMatrix;
import SLRPFL.formulas.OchiaiFormula;
import SLRPFL.spectrum.SpectrumRunner;

public class main {



    //args[0] : the path of coverage information
    //args[1] : the path of PDG data


    public static void main(String[] args) {
        /*if (args.length == 4) {
            CoverageMatrix coverageMatrix = new CoverageMatrix(args[0]);
            PDGAnalysis analysis = new PDGAnalysis(args[1]);
            SpectrumRunner spectrumRunner = new SpectrumRunner(coverageMatrix, analysis, args[2], args[3]);
            spectrumRunner.computeSuspiciousScore(new OchiaiFormula());
            spectrumRunner.computeDpSuspiciousScore(new OchiaiFormula());

        } else {
            System.out.print("ERROR ARGS!!!\n" + args.length);
        }*/
        /*CoverageMatrix coverageMatrix = new CoverageMatrix("Tests/flacoco/");
        PDGAnalysis analysis = new PDGAnalysis("Tests/resultPDG/");
        SpectrumRunner spectrumRunner = new SpectrumRunner(coverageMatrix, analysis);
        spectrumRunner.computeSuspiciousScore(new OchiaiFormula());
        spectrumRunner.computeDpSuspiciousScore(new OchiaiFormula());*/

        CoverageMatrix coverageMatrix = new CoverageMatrix("/home/arka/SLPRFL/Tests/flacoco/");
        PDGAnalysis analysis = new PDGAnalysis("/home/arka/SLPRFL/Tests/resultPDG/");
        SpectrumRunner spectrumRunner = new SpectrumRunner(coverageMatrix, analysis, "math_6_buggy",  "Results_Math/");
        spectrumRunner.computeSuspiciousScore(new OchiaiFormula());
        spectrumRunner.computeDpSuspiciousScore(new OchiaiFormula());


    }

}
