import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;


// jacek em 
public class FileListing {

    public static void main(String[] args) throws Exception {
        
     printFilesDetails("/Users/jacek.em/Desktop/jav");
     System.out.println("_____________________________");
     printFiles("/Users/jacek.em/Desktop/jav", ".txt");
     System.out.println("_____________________________");
     printTree("/Users/jacek.em/Desktop/jav");
        
    }
    
    
    public static void printFilesDetails(String path) throws Exception{
        
        File folder = new File(path);
        
        File[] listOfFiles = folder.listFiles();
        
        ArrayList<Integer> spaces = new ArrayList<>();
        
        for (File file: listOfFiles) {
            Integer len;
            len = file.getName().length();
            spaces.add(len);    
        }
        
        Integer maxlen = Collections.max(spaces);
        Integer blanks = maxlen + 5;
        
        for (File file: listOfFiles) {
            BasicFileAttributes attribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class );
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd mm:ss");
            System.out.printf("%"+"-"+blanks+"s" + " %-15s %s \n", 
                              file.getName(),
                              (attribute.isDirectory() == true ? "DIR" : attribute.size()),
                              df.format(attribute.creationTime().toMillis()) );
        }
        
    }
    
    //, String extensionFilter
    public static void printFiles(String path, String extensionFilter) {
        
        File folder = new File(path);
        FilenameFilter filter = new FilenameFilter() {
            // nie mam pojecia co sie dzieje
        @Override
        public boolean accept(File directory, String fileName) {
            return fileName.endsWith(extensionFilter);
            }
        };
        
        folder.listFiles(filter);
        for (String filename :folder.list(filter)){
            System.out.println(filename);
        }
        
    }
    
    public static void printTree(String path) throws Exception{

        Files.walk(Paths.get(path)).forEach(filePath -> System.out.println(filePath));


    }

    
}

