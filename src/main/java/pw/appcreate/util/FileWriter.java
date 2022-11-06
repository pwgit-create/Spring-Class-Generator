package pw.appcreate.util;

import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Created by Peter on 2020-05-23.
 */
public class FileWriter {


    public static void write(String fileName,String str)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));
        writer.write(str);

        writer.close();
    }




}
