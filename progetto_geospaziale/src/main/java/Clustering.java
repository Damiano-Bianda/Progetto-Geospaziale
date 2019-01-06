import java.util.*;


public class Clustering {

    /**
     * Applies the DBSCAN clustering algorithm with specified eps and minPoints.
     * Given a list of points labels them as NOISE (0) or with the id of the cluster they belong.
     * In order to work correctly initial value of the label must be NOISE for all points in the list.
     * @param points list of all points
     * @param eps the radius that define which elements are directly density-reachable from another
     * @param minPoints the number of points that a point must have in its neighborhood to be a core-point
     */
   public static void DBSCAN(ArrayList<Point> points, float eps, int minPoints){
       int clusterLabel = Point.NOISE + 1;
       for (Point point: points){
           if(point.isNoise()){
               final LinkedList<Point> queue = neighborhood(points, point, eps);
               if(queue.size() < minPoints) {
                   continue;
               }
               for(Point c: queue){
                   c.setLabel(clusterLabel);
               }
               queue.remove(point);
               while (!queue.isEmpty()){
                   final Point currentPoint = queue.remove(0);
                   final LinkedList<Point> currentNeighborhood = neighborhood(points, currentPoint, eps);
                   if (currentNeighborhood.size() >= minPoints){
                       for(Point currentNeighbor: currentNeighborhood){
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

    /**
     * Returns a list containing the neighborhood (directly density-reachable points) of a point comprised itself
     * @param points list of all points
     * @param point the examined point
     * @param eps the radius that define which elements are in the neighborhood
     * @return
     */
    private static LinkedList<Point> neighborhood(ArrayList<Point> points, Point point, float eps) {
        LinkedList<Point> neighborhood = new LinkedList<>();
        for (int i = 0; i < points.size(); i++) {
            Point possibleNeighbor = points.get(i);
            if (point.distance(possibleNeighbor) <= eps) {
                neighborhood.add(possibleNeighbor);
            }
        }
        return neighborhood;
    }


}
