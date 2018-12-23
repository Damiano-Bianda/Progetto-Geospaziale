import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class DBSCAN {

   /* public static final void algorithm(ArrayList<Coordinate> points, float eps, int minPoints){

        int clusterId = 1;
        for (int i = 0; i < points.size(); i++){
            final Coordinate point = points.get(i);
            if (point.isUnlabelled()){
                if (expandCluster(points, point, clusterId, eps, minPoints)) {
                    clusterId += 1;
                }
            }
        }

    }

    private static boolean expandCluster(ArrayList<Coordinate> points, Coordinate point, int clusterId, float eps, int minPoints) {
        ArrayList<Coordinate> neighborhood = getNeighborhood(points, point, eps);
        if(neighborhood.size() < minPoints){
            point.setLabel(Coordinate.NOISE);
            return false;
        } else {
            point.setLabel(clusterId);
            neighborhood.remove(point);
            while (!neighborhood.isEmpty()){
                Coordinate neighbor = neighborhood.get(neighborhood.size() - 1);
                ArrayList<Coordinate> newNeighborhood = getNeighborhood(points, neighbor, eps);
                if (newNeighborhood.size() >= minPoints){
                    for (int i = 0; i < newNeighborhood.size(); i++){
                        Coordinate newPoint = newNeighborhood.get(i);
                        if (newPoint.isUnlabelled() || newPoint.isNoise()){
                            if(newPoint.isUnlabelled()){
                                neighborhood.add(newPoint);
                            }
                            newPoint.setLabel(clusterId);
                        }
                    }
                }
                neighborhood.remove(neighbor);
            }
        }
        return true;
    }*/



    /*
    function DBSCAN(dataset, eps, minPoints)

        for each point in dataset:
            if the current point doesn't belong to a cluster:
                set its cluster as current cluster
                enqueue the point
                while the queue contains elements:
                    remove the head of the queue
                    set current point as head
                    find the neighborhood of current point
                    if current point is a core point:
                        for each neighbor in neighborhood:
                            if neighbor doesn't belong to a cluster:
                                set its cluster as current cluster
                            enqueue new labelled neighbors
                if the current cluster contains only current point:
                    label the point as noise
                else

        noiseId = 0
        for each point in dataset:
            point.cluster = noiseId

        clusterId = 1
        For each point in dataset:
            if point.cluster == noiseId:
                point.cluster = clusterId
                queue.enqueue(point)
            while queue is not empty:
                currentPoint = queue.removeHead()
                if currentPoint is a core point:
                    for each directly density-reachable neighbor:
                        if neighbor is noise:
                            neighbor.cluster = clusterId
                            queue.enqueue(neighbor)
            if size of current cluster is one:
                point.cluster = noiseId
            else:
                clusterId += 1*/





    public static void algorithm(ArrayList<Coordinate> coordinates, float eps, int minPoints){
        int clusterLabel = Coordinate.NOISE + 1;
        for (Coordinate coordinate: coordinates){
            if(coordinate.isNoise()){
                final ArrayList<Coordinate> queue = getNeighborhood(coordinates, coordinate, eps);
                if(queue.size() < minPoints) {
                    continue;
                }
                for(Coordinate c: queue){
                    if(!c.isNoise()){
                        System.out.println("ERROR");
                    }
                    c.setLabel(clusterLabel);
                }
                queue.remove(coordinate);
                coordinate.setLabel(clusterLabel);
                while (!queue.isEmpty()){
                    final Coordinate parent = queue.remove(0);
                    final ArrayList<Coordinate> children = getNeighborhood(coordinates, parent, eps);
                    if (children.size() >= minPoints){
                        for(Coordinate child: children){
                            if(child.isNoise()){
                                queue.add(child);
                                child.setLabel(clusterLabel);
                            }
                        }
                    }
                }
                clusterLabel++;
            }
        }
    }

    private static ArrayList<Coordinate> getNeighborhood(ArrayList<Coordinate> points, Coordinate point, float eps) {
        ArrayList<Coordinate> neighborhood = new ArrayList<>();
        for(int i = 0; i < points.size(); i++){
            Coordinate possibleNeighbor = points.get(i);
            // TODO < o < = ?
            if(point.distance(possibleNeighbor) < eps){
                neighborhood.add(possibleNeighbor);
            }
        }
        return neighborhood;
    }

    public static void main(String[] args){

        String path = "C:\\Users\\damia\\Downloads\\animale.csv";

        ArrayList<Coordinate> coordinates;
        float eps = 300;
        int minPoints = 20;
        coordinates = new ArrayList<Coordinate>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            boolean firstLine = true;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                } else {
                    String[] split = line.split(",");
                    coordinates.add(new Coordinate(
                            Integer.parseInt(split[1]),
                            new Double(split[3]),
                            new Double(split[4])));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File \""+ path +"\" not found, see error.log");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("File \""+ path +"\" not found, see error.log");
            System.exit(2);
        }

        /*eps = 3.0f;
        minPoints = 2;
        coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0 ,0 ));
        coordinates.add(new Coordinate(1, 6 ,1 ));
        coordinates.add(new Coordinate(2, 0 ,1 ));
        coordinates.add(new Coordinate(3, 1 ,1 ));
        coordinates.add(new Coordinate(4, 5 ,1 ));
        coordinates.add(new Coordinate(5, 0 ,2 ));
        coordinates.add(new Coordinate(6, 6 ,2 ));
        coordinates.add(new Coordinate(7, 8 ,4 ));
        coordinates.add(new Coordinate(8, 8 ,5 ));*/

        /*for (int i = 0; i < 1000; i++){

        }*/
        Collections.shuffle(coordinates);
        DBSCAN.algorithm(coordinates, eps, minPoints);

        for (Coordinate coordinate: coordinates){
            System.out.println(coordinate);
        }

        /*Coordinate c255 = coordinates.get(255);
        Coordinate c340 = coordinates.get(340);
        Coordinate c341 = coordinates.get(341);
        Coordinate c473 = coordinates.get(473);
        Coordinate c486 = coordinates.get(486);

        System.out.println("end");*/



    }
}
