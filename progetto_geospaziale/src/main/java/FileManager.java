import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    /**
     * Reads specified CSV file from filesystem and parse it to obtain id, x, y attributes
     * @param file
     * @return A list of points each of them containing parsed attributes
     * @throws Exception if file can not be found, or there is a java IO Exception
     */
    public static ArrayList<Point> read(File file) throws Exception {
        ArrayList<Point> points = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            boolean firstLine = true;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(firstLine){
                    firstLine = false;
                } else {
                    String[] split = line.split(",");
                    points.add(new Point(
                            Integer.parseInt(split[1]),
                            new Double(split[3]),
                            new Double(split[4])));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new Exception("File not found");
        } catch (IOException e) {
            throw new Exception("Java IOException during lecture of file\n" + e.getMessage());
        }
        return points;
    }

    /**
     * Given a list of points creates a file named fileName containing points in CSV format
     * @param points
     * @param fileName
     * @throws Exception if there is a JavaIOException
     */
    public static void write(List<Point> points, String fileName) throws Exception {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(Point.getCSVHeader() + "\n");
            for(Point point : points){
                bufferedWriter.write(point.getCSVRow() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new Exception("Java IOException during writing of file\n" + e.getMessage());
        }
    }

}
