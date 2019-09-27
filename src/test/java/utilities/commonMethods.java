package utilities;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class commonMethods {



    public static String loadFile(String path) throws IOException {
        return IOUtils.toString(commonMethods.class.getClassLoader().getResourceAsStream(path),
                Charset.forName("UTF-8"));
    }

    public static void writeOnFile(String path,String id) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(id);
        writer.close();
    }

    public static String readFromFile(String path) throws IOException {

        Scanner myScanner = new Scanner(new File(path));
         String id = null;
        while (myScanner.hasNextLine()) {
              id = myScanner.nextLine();
            //parse the string for your data and store it

        }
        return id;
    }
}
