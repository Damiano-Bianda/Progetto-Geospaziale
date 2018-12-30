import java.util.Objects;

public class Coordinate {

    private static double[][] distanceMatrix;
    private int id;
    private double x;
    private double y;
    private int label = NOISE;
    public static final int NOISE = 0;
   // public static final int UNLABELLED = -1;

    public Coordinate(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }


    public double distance(Coordinate coordinate){
        return Math.sqrt(Math.pow(coordinate.x - this.x, 2) + Math.pow(coordinate.y - this.y , 2));
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

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

   /*public boolean isUnlabelled() {
        return this.label == Coordinate.UNLABELLED;
    }*/

    public boolean isNoise() {
        return this.label == Coordinate.NOISE;
    }

    public int getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return id + "," + x + ","+ y + "," + label;
    }
}
