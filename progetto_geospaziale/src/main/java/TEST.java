import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TEST {

    public static void main(String[] args){

        int id = 0;

        Random random = new Random();

        ArrayList<Point> coordinates = new ArrayList<Point>();
        for (float i = 0; i < 2 * Math.PI; i+= Math.PI/6){

            for(int j = 0; j < 50; j++){
                coordinates.add(new Point(id++, Math.cos(i) * 50 + (random.nextFloat() - 0.5) * 20, Math.sin(i) * 50 + (random.nextFloat() - 0.5) * 20));
            }
            System.out.println(i);
        }

        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.csv"));
            bufferedWriter.write( "animal,id,time,x,y\n");
            for(Point point : coordinates){
                bufferedWriter.write("1," + point.getId() + ",bos," +point.getX() +","+ point.getY() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
        }

    }
}
