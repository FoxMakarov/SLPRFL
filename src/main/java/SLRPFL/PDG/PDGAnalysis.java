package SLRPFL.PDG;

import SLRPFL.Struct.Location;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

import SLRPFL.Struct.PDGEdge;
import SLRPFL.Struct.PDGNode;
import org.apache.commons.io.FileUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class PDGAnalysis {

    private String FolderPath = "";

    //suffix keywords
    private String KeyWords = "PDG-DATA.json";

    private String ExclusiveWord = "Test-PDG-DATA.json";

    private List<File> PDGJsonFileList;

    private Map<Location, List<Location> > dependencyMatrix = new HashMap<>();

    public PDGAnalysis(String folderPath){
        FolderPath = folderPath;
        PDGJsonFileList = searchFolder();
        analyseDependencyMatrix();
    }

    private List<File> searchFolder() {
        File folder = new File(FolderPath);

        List<File> result = new ArrayList<>();

        if(!folder.isFile()) {

            File[] subFolders = folder.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if(pathname.isDirectory()) {
                        return true;
                    }

                    if(pathname.getName().endsWith(KeyWords) && !pathname.getName().endsWith(ExclusiveWord)) {
                        return true;
                    }

                    return false;
                }
            });

            if (subFolders != null) {
                for(File file : subFolders) {
                    if(file.isFile()) {
                        result.add(file);
                    } else {
                        //ignored ...
                        result.addAll(searchFolder());
                    }

                }
            }


        } else {
            System.out.print("The folder location is just a file");
        }


        return result;
    }

    //ignore False Control Edge in for/while

    private void analyseDependencyMatrix() {
        try{
            for(File file : PDGJsonFileList) {
                String input = FileUtils.readFileToString(file, "UTF-8");
                JSONObject jsonObject = JSONObject.fromObject(input);

                if(jsonObject == null) {
                    continue;
                }

                String fileName = jsonObject.getString("file");
                //delete ".java" from file name
                fileName.substring(0, fileName.length() - 5);

                JSONArray nodeArray = jsonObject.getJSONArray("nodes");
                Iterator<Object>node = nodeArray.iterator();
                Vector<PDGNode> nodes = new Vector<PDGNode>();
                while(node.hasNext()) {
                    JSONObject currentNode = (JSONObject)node.next();
                    nodes.add(new PDGNode(currentNode.getInt("line")));
                }

                JSONArray edgeArray = jsonObject.getJSONArray("edges");
                Iterator<Object>edge = edgeArray.iterator();
                while(edge.hasNext()){
                    JSONObject currentEdge = (JSONObject)edge.next();

                    //analyse the dependency
                    int source = currentEdge.getInt("source");
                    int target = currentEdge.getInt("target");
                    String type = currentEdge.getString("type");
                    String label = currentEdge.getString("label");

                    if(nodes.get(source).getLine() == 0 || nodes.get(target).getLine() == 0 || label == "") {
                        //ignore the edge is used at the nodes "endFor"."endWhile","endIf"
                        //ignore the useless control edge
                        continue;
                    }

                    Location srcLocation = new Location(fileName, nodes.get(source).getLine());
                    Location trgLocation = new Location(fileName, nodes.get(target).getLine());

                    if(dependencyMatrix.containsKey(srcLocation)) {
                        List<Location> locationList = dependencyMatrix.get(srcLocation);
                        locationList.add(trgLocation);
                        dependencyMatrix.put(srcLocation, locationList);
                    } else {
                        List<Location> locationList = new ArrayList<>();
                        locationList.add(trgLocation);
                        dependencyMatrix.put(srcLocation, locationList);
                    }
                }
            }
        } catch(IOException e) {
            System.out.print("JSON DEAL ERROR!");
        }
    }

    public Map<Location, List<Location> > getDependencyMatrix() {
        return dependencyMatrix;
    }
}
