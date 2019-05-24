package filefindermergesort;

import java.io.IOException;

/**
 *
 * @author pieterkoopman
 */
public class FileFinderMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         FileFinderTest();
    }

    public static void FileFinderTest() {
        try {
            String goal = "FileFinder.java";
            String root = "C:\\";
            FileFinder ff = new FileFinder(root);
            ff.findFile(goal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
