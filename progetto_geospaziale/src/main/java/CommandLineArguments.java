import java.io.File;

public class CommandLineArguments {

    private final File file;
    private final float eps;
    private final int minPoints;

    /**
     * Checks command line arguments specified at program launch and creates an object containing them
     * @param argv
     * @throws Exception: if file doesn't exist, parameters are invalid or they are too less or too much
     */
    public CommandLineArguments(String[] argv) throws Exception {

        if(argv.length != 3){
            throw new Exception("Run the program with:\nprogramName \"C:\\\\path\\\\to\\\\file\" eps minPoints");
        }

        this.file = new File(argv[0]);

        if(!file.exists()){
            throw new Exception("File not found: "+ argv[0]);
        } else if(!file.isFile()){
            throw new Exception("Resource at path: " + argv[0] + " is not a file");
        }

        try {
            this.eps = Float.parseFloat(argv[1]);
        } catch(NumberFormatException e){
            throw new Exception("Eps must be a float number");
        }

        if(this.eps < 0){
            throw new Exception("Eps can't be less than 0");
        }

        try {
            this.minPoints = Integer.parseInt(argv[2]);
        } catch(NumberFormatException e){
            throw new Exception("MinPoints must be an integer number");
        }

        if(this.minPoints < 0){
            throw new Exception("MinPoints can't be less than 0");
        }
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return eps parameters for DBSCAN
     */
    public float getEps() {
        return eps;
    }

    /**
     * @return minPoints parameter for DBSCAN
     */
    public int getMinPoints() {
        return minPoints;
    }
}
