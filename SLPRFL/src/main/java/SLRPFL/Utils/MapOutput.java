package SLRPFL.Utils;

import SLRPFL.Struct.Location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class MapOutput {

    public static void output(String path, Map<Location, Double>map, int number) {
        try{
            File resultOutput = new File(path);
            if(!resultOutput.exists()) {
                resultOutput.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(resultOutput.getPath());
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            if(map.size() < number) {
                for(Map.Entry<Location, Double> entry : map.entrySet()) {
                    Location key = entry.getKey();
                    Double value = entry.getValue();
                    bufferWriter.write( key.print() + " " + (Double)value + "\n");
                }
            } else {
                int now = 0;
                for(Map.Entry<Location, Double> entry : map.entrySet()) {
                    Location key = entry.getKey();
                    Double value = entry.getValue();
                    bufferWriter.write(key.print() + " " + (Double)value + "\n");

                    now++;
                    if(now >= number) break;
                }
            }

            bufferWriter.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
