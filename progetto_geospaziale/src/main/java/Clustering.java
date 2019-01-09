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
        int clusterId = Point.NOISE + 1;
        for (Point point: points){
            if (!point.isVisited()){
                boolean clusterCreated = false;
                point.setLabel(Point.NOISE);
                LinkedList<Point> queue = new LinkedList<>();
                queue.add(point);
                while(!queue.isEmpty()){
                    Point p = queue.remove(0);
                    LinkedList<Point> neighborhood = neighborhood(points, p, eps);
                    if(isCorePoint(minPoints, neighborhood)){
                        for (Point neighbor: neighborhood){
                            if(!neighbor.belongToACluster()) {
                                if(!neighbor.isVisited()){
                                    queue.add(neighbor);
                                }
                                neighbor.setLabel(clusterId);
                            }
                        }
                        clusterCreated = true;
                    }
                }
                if(clusterCreated){
                    clusterId++;
                }
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

    /**
     * Check whether a point is a core point based on its neighborhood
     * @param minPoints     minPoints DBSCAN parameter
     * @param neighborhood  array containing the neighborhood of the point
     * @return true if it is a core point, false otherwise
     */
    private static boolean isCorePoint(int minPoints, LinkedList<Point> neighborhood) {
        return neighborhood.size() >= minPoints;
    }
}
