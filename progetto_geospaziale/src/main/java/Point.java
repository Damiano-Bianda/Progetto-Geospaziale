import java.util.Objects;

public class Point {

    private int id;
    private double x;
    private double y;
    private int label = NOISE;
    public static final int NOISE = 0;

    /**
     * Builds a point with specified parameters and cluster label set to NOISE (0)
     * @param id:   an id that refer to a specific points
     * @param x:    the x coordinate
     * @param y:    the y coordinate
     */
    public Point(int id, double x, double y) {
        this.id = id;
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
     * Constructs a CSV string in format "id,x,y,label"
     * @return a string representing this point in CSV format
     */
    public String getCSVRow() {
        return id + "," + x + ","+ y + "," + label;
    }

    /**
     * Constructs the CSV header for the Point datatype "id,x,y,label"
     * @return a string representing the header in CSV format
     */
    public static String getCSVHeader(){
        return "id,x,y,label";
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

    public int getLabel() {
        return this.label;
    }


    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
