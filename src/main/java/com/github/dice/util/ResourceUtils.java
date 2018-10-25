package com.github.dice.util;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ResourceUtils {

    public static String readResource(String path) throws FileNotFoundException{
        Reader reader = null;
        try {
            reader = getResourceReader(path);
            Scanner s = new Scanner(reader).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        finally {
            if(null != reader){
                try {
                    reader.close();
                }
                catch (IOException ex2){
                    throw new RuntimeException(ex2);
                }
            }
        }

    }

    public static Reader getResourceReader(String name) throws FileNotFoundException{
        InputStream is = null;
        try {
            is = ResourceUtils.class.getClassLoader().getResourceAsStream(getCPResourcePath(name));
            if (is == null) {
                is = new FileInputStream(new File(name));
            }
            return new InputStreamReader(is);
        }
        catch (FileNotFoundException ex){
            throw ex;
        }
    }

    public static String getCPResourcePath(String name) {
        if (!"/".equals(File.separator)) {
            return name.replaceAll(Pattern.quote(File.separator), "/");
        }
        return name;
    }
}
