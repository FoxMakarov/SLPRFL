import os
import sys


def runCMDLang():
    for i in range(3, 66):
        if(i == 2):
            continue
        version = i 
        cmd = "defects4j checkout -p Lang -v %sb -w /home/arka/SLPRFL/Tests/defects4j/Lang/lang_%s_buggy" %(version, version)
        os.system(cmd)

def runCMDCli():
    for i in range(2, 41):
        if(i == 6):
            continue
        version = i
        cmd = "defects4j checkout -p Cli -v %sb -w /home/arka/SLPRFL/Tests/defects4j/Cli/cli_%s_buggy\ndefects4j test" %(version, version)
        os.system(cmd)

def runCMDChart():
    for i in range(1, 27):

        version = i
        cmd = "defects4j checkout -p Chart -v %sb -w /home/arka/SLPRFL/Tests/defects4j/Chart/chart_%s_buggy\ncd /home/arka/SLPRFL/Tests/defects4j/Chart/chart_%s_buggy\ndefects4j test" %(version, version, version)
        os.system(cmd)

def runCMDMath():
    for i in range(2, 107):

        version = i
        cmd = "defects4j checkout -p Math -v %sb -w /home/arka/SLPRFL/Tests/defects4j/Math/math_%s_buggy\ncd /home/arka/SLPRFL/Tests/defects4j/Math/math_%s_buggy\ndefects4j test" %(version, version,version)
        os.system(cmd)

def runCMDMockito():
    for i in range(1, 39):

        version = i
        cmd = "defects4j checkout -p Mockito -v %sb -w /home/arka/SLPRFL/Tests/defects4j/Mockito/mockito_%s_buggy\ncd /home/arka/SLPRFL/Tests/defects4j/Mockito/mockito_%s_buggy\ndefects4j test" %(version, version,version)
        os.system(cmd)

def runCMDTime():
    for i in range(2, 28):

        if(i == 21):
            continue

        version = i
        cmd = "defects4j checkout -p Time -v %sb -w /home/arka/SLPRFL/Tests/defects4j/Time/time_%s_buggy\ncd /home/arka/SLPRFL/Tests/defects4j/Time/time_%s_buggy\ndefects4j test" %(version, version,version)
        os.system(cmd)



if __name__ == "__main__":
        runCMDTime()
    

