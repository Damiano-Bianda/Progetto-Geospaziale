import java.io.*;
import java.util.*;


public class DBSCAN {

   /*public static final void algorithm(ArrayList<Coordinate> points, float eps, int minPoints){

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
        ArrayList<Coordinate> neighborhood = neighborhood(points, point, eps);
        if(neighborhood.size() < minPoints){
            point.setLabel(Coordinate.NOISE);
            return false;
        } else {
            point.setLabel(clusterId);

            for(Coordinate coordinate: neighborhood){

                coordinate.setLabel(clusterId);
            }

            neighborhood.remove(point);
            while (!neighborhood.isEmpty()){
                Coordinate neighbor = neighborhood.get(neighborhood.size() - 1);
                ArrayList<Coordinate> newNeighborhood = neighborhood(points, neighbor, eps);
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


    /*

void initDataSet(dataset):
    foreach point in points:
            point.cluster := NOISE

bool isCorePoint(neighborhood, minPoints):
    return neighborhood.size() >= minPoints

List<Point> neighborhood(points , point, epsilon):
    neighbors = []
    foreach candidateNeighbor in points:
        if distance(candidateNeighbor, point) <= epsilon:
            neighbors.add(candidateNeighbor)
    return neighbors

float distance(pointA, pointB):
    return sqrt((pointA.x - pointB.x)^2 + (pointA.y - pointB.y)^2))

void DBSCAN(points, epsilon, minPoints):

    initDataSet(points)

    clusterLabel := 1

    foreach point in points:
        if point.cluster := NOISE:
            neighbors := neighborhood(points, point, epsilon)
            if isCorePoint(neighborhood, minPoints):
                foreach neighbor in neighbors:
                    neighbor.cluster := clusterLabel
                neighbors.remove(point)
                while neighbors.size() > 0:
                    currentPoint = neighbors.getFirst()
                    currentNeighborhood = neighborhood(points, currentPoint, epsilon)
                    if isCorePoint(currentNeighborhood, currentPoint):
                        foreach currentNeighbor in currentNeighborhood:
                            if currentNeighbor.cluster = NOISE
                                neighbor.add(currentNeighbor)
                                neighbor.cluster = clusterLabel
            clusterLabel := clusterLabel + 1


     */


 public static void algorithm(ArrayList<Coordinate> points, float eps, int minPoints){
        int clusterLabel = Coordinate.NOISE + 1;
        for (Coordinate point: points){
            if(point.isNoise()){
                final ArrayList<Coordinate> queue = neighborhood(points, point, eps);
                if(queue.size() < minPoints) {
                    continue;
                }
                for(Coordinate c: queue){
                    c.setLabel(clusterLabel);
                }
                queue.remove(point);
                //point.setLabel(clusterLabel); // credo che non serve
                while (!queue.isEmpty()){
                    final Coordinate currentPoint = queue.remove(0);
                    final ArrayList<Coordinate> currentNeighborhood = neighborhood(points, currentPoint, eps);
                    if (currentNeighborhood.size() >= minPoints){
                        for(Coordinate currentNeighbor: currentNeighborhood){
                            if(currentNeighbor.isNoise()){
                                queue.add(currentNeighbor);
                                currentNeighbor.setLabel(clusterLabel);
                            }
                        }
                    }
                }
                clusterLabel++;
            }
        }
    }


    private static ArrayList<Coordinate> neighborhood(ArrayList<Coordinate> points, Coordinate point, float eps) {
        ArrayList<Coordinate> neighborhood = new ArrayList<>();



        for(int i = 0; i < points.size(); i++){


            Coordinate possibleNeighbor = points.get(i);

            if(point.distance(possibleNeighbor) <= eps){
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
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File \""+ path +"\" not found, see error.log");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("File \""+ path +"\" not found, see error.log");
            System.exit(2);
        }

        /*eps = 1.0f;
        minPoints = 0;
        coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0 ,0 ));
        coordinates.add(new Coordinate(1, 6 ,0 ));
        coordinates.add(new Coordinate(2, 0 ,1 ));
        coordinates.add(new Coordinate(3, 1 ,1 ));
        coordinates.add(new Coordinate(4, 5 ,1 ));
        coordinates.add(new Coordinate(5, 6 ,1 ));
        coordinates.add(new Coordinate(6, 0 ,2 ));
        coordinates.add(new Coordinate(7, 6 ,2 ));
        coordinates.add(new Coordinate(8, 8 ,8 ));*/


        /*coordinates.clear();

        eps = 15;
        minPoints = 100;

        for(int angle = 0; angle < 360; angle+=10){
            for(int i=0 ;i < 50; i++){
                coordinates.add(new Coordinate(i+1, Math.cos(angle)*100 + Math.random()*20, Math.sin(angle)* 100 + Math.random()*20));
            }
        }*/
/*
        for(int i = 0; i < 50; i++){
            coordinates.add(new Coordinate(i + 1, Math.random()*eps + 50, Math.random()* eps + 50));
        }
        for(int i = 50; i < 100; i++){
            coordinates.add(new Coordinate(i + 1, 400 + Math.random()*eps + 50, 400 + Math.random()* eps + 50));
        }*/



        //Collections.shuffle(coordinates);
        final long start = System.currentTimeMillis();
        DBSCAN.algorithm(coordinates, eps, minPoints);
        System.out.println(System.currentTimeMillis() - start);

    /*   for (Coordinate coordinate: coordinates){
            System.out.println(coordinate);
        }*/


        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("results.csv"));
            bufferedWriter.write("ID,X,Y,LABEL\n");
            for(Coordinate coordinate: coordinates){
                bufferedWriter.write(coordinate.toString() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
