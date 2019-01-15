import java.util.Objects;

public class Point {

    private int animal;
    private int id;
    private String time;
    private double x;
    private double y;
    private int label = UNVISITED;
    public static final int UNVISITED = -1;
    public static final int NOISE = 0;

    /**
     * Builds a point with specified parameters and cluster label set to NOISE (0)
     * @param id:   an id that refer to a specific points
     * @param x:    the x coordinate
     * @param y:    the y coordinate
     */
    public Point(int animal, int id, String time, double x, double y) {
        this.animal = animal;
        this.id = id;
        this.time = time;
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the euclidean distance between this point and another
     * @param point: the other point
     * @return: the euclidean distance
     */
    public double distance(Point point){
        return Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y , 2));
    }

    /**
     * Sets the cluster label of this point to a new value
     * @param label
     */
    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * Checks if this point is labelled as NOISE
     * @return true if label is NOISE, false otherwise
     */
    public boolean isNoise() {
        return this.label == Point.NOISE;
    }


    /**
     * Checks if this point belong to a cluster
     * @return true if label has the id of a cluster, false otherwise
     */
    public boolean belongToACluster(){
        return this.label > 0;
    }

    /**
     * Checks if this point has already been visited, i.e. if it is NOISE or belong to a cluster
     * @return true il label is UNVISITED, false otherwise
     */
    public boolean isVisited(){
        return this.label != -1;
    }

    /**
     * Constructs a CSV string in format "id,x,y,label"
     * @return a string representing this point in CSV format
     */
    public String getCSVRow() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.animal).append(",")
        .append(this.id).append(",")
        .append(this.time).append(",")
        .append(this.x).append(",")
        .append(this.y).append(",")
        .append(this.label);
        return sb.toString();
    }

    /**
     * Constructs the CSV header for the Point datatype "id,x,y,label"
     * @return a string representing the header in CSV format
     */
    public static String getCSVHeader(){
        return "animal,id,time,x,y,label";
    }

    /**
     * A point is considered equals to another if they have same id
     * @param o
     * @return true if objects are equals, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return id == that.id;
    }

    /**
     * Hashcode is calculated based on id
     * @return the hashcode of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }
}
