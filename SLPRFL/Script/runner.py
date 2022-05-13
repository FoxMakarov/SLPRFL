import os
import sys
import shutil


def runCMDLang():
    for i in range(51, 52):
        shutil.rmtree("/home/arka/SLPRFL/Tests/resultPDG")
        os.mkdir("/home/arka/SLPRFL/Tests/resultPDG")

        if(i == 2):
            continue
        version = i
        print("*************run flacoco for lang_%d_buggy***************"%(version))
        cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Lang/lang_%s_buggy/"%(version)
        os.system(cmd1)

        print("*************run PDG for lang_%d_buggy***************"%(version))
        cmd2 = "cd /home/arka/SLPRFL/Tests/progex/\njava -jar target/progex-3.4.5.jar -outdir ../resultPDG/ -format json -pdg ../defects4j/Lang/lang_%s_buggy/src"%(version)
        os.system(cmd2)

        print("*************run SLPRFL for lang_%d_buggy***************"%(version))
        cmd3 = "cd /home/arka/SLPRFL/\njava  -jar target/SLPRFL-1.0-SNAPSHOT-jar-with-dependencies.jar /home/arka/SLPRFL/Tests/flacoco/ /home/arka/SLPRFL/Tests/resultPDG/ lang_%s_buggy Results_Lang/"%(version)
        os.system(cmd3)

def runCMDCli():
    for i in range(51, 52):
        shutil.rmtree("/home/arka/SLPRFL/Tests/resultPDG")
        os.mkdir("/home/arka/SLPRFL/Tests/resultPDG")

        if(i == 2):
            continue
        version = i
        print("*************run flacoco for lang_%d_buggy***************"%(version))
        cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Lang/lang_%s_buggy/"%(version)
        os.system(cmd1)

        print("*************run PDG for lang_%d_buggy***************"%(version))
        cmd2 = "cd /home/arka/SLPRFL/Tests/progex/\njava -jar target/progex-3.4.5.jar -outdir ../resultPDG/ -format json -pdg ../defects4j/Lang/lang_%s_buggy/src"%(version)
        os.system(cmd2)

        print("*************run SLPRFL for lang_%d_buggy***************"%(version))
        cmd3 = "cd /home/arka/SLPRFL/\njava  -jar target/SLPRFL-1.0-SNAPSHOT-jar-with-dependencies.jar /home/arka/SLPRFL/Tests/flacoco/ /home/arka/SLPRFL/Tests/resultPDG/ lang_%s_buggy Results_Lang/"%(version)
        os.system(cmd3)

def runCMDMath():
    for i in range(7, 107):
        shutil.rmtree("/home/arka/SLPRFL/Tests/resultPDG")
        os.mkdir("/home/arka/SLPRFL/Tests/resultPDG")

        version = i
        print("*************run flacoco for math_%d_buggy***************"%(version))
        cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Math/math_%s_buggy/"%(version)
        os.system(cmd1)

        print("*************run PDG for math_%d_buggy***************"%(version))
        cmd2 = "cd /home/arka/SLPRFL/Tests/progex/\njava -Xmx2048m -jar target/progex-3.4.5.jar -outdir ../resultPDG/ -format json -pdg ../defects4j/Math/math_%s_buggy/src"%(version)
        os.system(cmd2)

        print("*************run SLPRFL for math_%d_buggy***************"%(version))
        cmd3 = "cd /home/arka/SLPRFL/\njava -Xmx2048m -jar target/SLPRFL-1.0-SNAPSHOT-jar-with-dependencies.jar /home/arka/SLPRFL/Tests/flacoco/ /home/arka/SLPRFL/Tests/resultPDG/ math_%s_buggy Results_Math/"%(version)
        os.system(cmd3)


def runCMDChart():
    for i in range(1, 27):
        shutil.rmtree("/home/arka/SLPRFL/Tests/resultPDG")
        os.mkdir("/home/arka/SLPRFL/Tests/resultPDG")

        version = i
        print("*************run flacoco for chart_%d_buggy***************"%(version))
        cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Chart/chart_%s_buggy/ --srcJavaDir ../defects4j/Chart/chart_%s_buggy/source/ --srcTestDir ../defects4j/Chart/chart_%s_buggy/tests/ --binTestDir ../defects4j/Chart/chart_%s_buggy/build-tests --binJavaDir ../defects4j/Chart/chart_%s_buggy/build"%(version, version, version, version, version)
        os.system(cmd1)

        print("*************run PDG for chart_%d_buggy***************"%(version))
        cmd2 = "cd /home/arka/SLPRFL/Tests/progex/\njava -Xmx2048m -jar target/progex-3.4.5.jar -outdir ../resultPDG/ -format json -pdg ../defects4j/Chart/chart_%s_buggy/source"%(version)
        os.system(cmd2)

        print("*************run SLPRFL for chart_%d_buggy***************"%(version))
        cmd3 = "cd /home/arka/SLPRFL/\njava -Xmx2048m -jar target/SLPRFL-1.0-SNAPSHOT-jar-with-dependencies.jar /home/arka/SLPRFL/Tests/flacoco/ /home/arka/SLPRFL/Tests/resultPDG/ chart_%s_buggy Results_Chart/"%(version)
        os.system(cmd3)

def runCMDMockito():
    for i in range(12, 39):
        shutil.rmtree("/home/arka/SLPRFL/Tests/resultPDG")
        os.mkdir("/home/arka/SLPRFL/Tests/resultPDG")

        version = i
        print("*************run flacoco for mockito_%d_buggy***************"%(version))
        #cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Mockito/mockito_%s_buggy/ --srcJavaDir ../defects4j/Mockito/mockito_%s_buggy/src/ --srcTestDir ../defects4j/Mockito/mockito_%s_buggy/test/ --binTestDir ../defects4j/Mockito/mockito_%s_buggy/build/classes/test/ --binJavaDir ../defects4j/Mockito/mockito_%s_buggy/build/classes/main/"%(version, version, version, version, version)
        cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Mockito/mockito_%s_buggy/ --srcJavaDir ../defects4j/Mockito/mockito_%s_buggy/src/ --srcTestDir ../defects4j/Mockito/mockito_%s_buggy/test/"%(version, version, version)
        os.system(cmd1)

        print("*************run PDG for mockito_%d_buggy***************"%(version))
        cmd2 = "cd /home/arka/SLPRFL/Tests/progex/\njava -Xmx2048m -jar target/progex-3.4.5.jar -outdir ../resultPDG/ -format json -pdg ../defects4j/Mockito/mockito_%s_buggy/src/"%(version)
        os.system(cmd2)

        print("*************run SLPRFL for chart_%d_buggy***************"%(version))
        cmd3 = "cd /home/arka/SLPRFL/\njava -Xmx2048m -jar target/SLPRFL-1.0-SNAPSHOT-jar-with-dependencies.jar /home/arka/SLPRFL/Tests/flacoco/ /home/arka/SLPRFL/Tests/resultPDG/ mockito_%s_buggy Results_Mockito/"%(version)
        os.system(cmd3)

def runCMDTime():
    for i in range(17, 28):
        shutil.rmtree("/home/arka/SLPRFL/Tests/resultPDG")
        os.mkdir("/home/arka/SLPRFL/Tests/resultPDG")

        if(i == 21):
            continue
        version = i
        print("*************run flacoco for time_%d_buggy***************"%(version))
        cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Time/time_%s_buggy/ --binJavaDir ../defects4j/Time/time_%s_buggy/build/classes --binTestDir ../defects4j/Time/time_%s_buggy/build/tests"%(version, version, version)
        #cmd1 = "cd /home/arka/SLPRFL/Tests/flacoco/\njava -jar target/flacoco-1.0.3-SNAPSHOT-jar-with-dependencies.jar --projectpath ../defects4j/Time/time_%s_buggy/"%(version)
        os.system(cmd1)

        print("*************run PDG for time_%d_buggy***************"%(version))
        cmd2 = "cd /home/arka/SLPRFL/Tests/progex/\njava -Xmx2048m -jar target/progex-3.4.5.jar -outdir ../resultPDG/ -format json -pdg ../defects4j/Time/time_%s_buggy/src"%(version)
        os.system(cmd2)

        print("*************run SLPRFL for time_%d_buggy***************"%(version))
        cmd3 = "cd /home/arka/SLPRFL/\njava -Xmx4096m -jar target/SLPRFL-1.0-SNAPSHOT-jar-with-dependencies.jar /home/arka/SLPRFL/Tests/flacoco/ /home/arka/SLPRFL/Tests/resultPDG/ time_%s_buggy Results_Time/"%(version)
        os.system(cmd3)


if __name__ == "__main__":
    runCMDTime()
    

