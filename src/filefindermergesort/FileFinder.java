package filefindermergesort;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author pieterkoopman
 */
public class FileFinder {

    private final File rootDir;
    long start = System.currentTimeMillis();

    public FileFinder(String root) throws IOException {
        rootDir = new File(root);
        if (!(rootDir.exists() && rootDir.isDirectory())) {
            throw new IOException(root + " is not a directory");
        }
    }

    public void findFile(String file) {
        find(rootDir, file);
    }

    private void find(File rootDir, String fileName) {
        File[] files = rootDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    //return;
                    System.out.println("Found at: " + file.getAbsolutePath() + " in " + (System.currentTimeMillis() - start) + " ms ");
                } else if (file.isDirectory()) {
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            find(file, fileName);
                        }
                    });
                    t.start();
                }
            }
        }
    }
}
