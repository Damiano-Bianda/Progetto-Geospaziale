import java.util.ArrayList;

public class App {

    private static final String resultsFileName = "results.csv";

    /**
     * Main program
     * @param args
     */
    public static void main(String[] args){

        // checks if command line arguments are correct and create an object containing them
        CommandLineArguments commandLineArguments = null;
        try {
            commandLineArguments = new CommandLineArguments(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // reads the file to obtain a list of 2D points
        ArrayList<Point> points = null;
        try {
            points = FileManager.read(commandLineArguments.getFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(2);
        }

        // executes DBSCAN clustering algorithm
        long start = System.currentTimeMillis();
        Clustering.DBSCAN(points, commandLineArguments.getEps(), commandLineArguments.getMinPoints());
        System.out.println(System.currentTimeMillis() - start);

        // writes results of DBSCAN on a new file
        try {
            FileManager.write(points, resultsFileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(3);
        }

        for(Point point: points){
            point.setLabel(-1);
        }

        start = System.currentTimeMillis();
        Clustering.DBSCAN2(points, commandLineArguments.getEps(), commandLineArguments.getMinPoints());
        System.out.println(System.currentTimeMillis() - start);

        try {
            FileManager.write(points, resultsFileName + 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(3);
        }

        System.out.println(resultsFileName + " file has been created.");

    }
}
