package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyRead {
    public static String readAsString(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            String content = stringBuilder.toString();
            return content;
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            Path path = Paths.get(filePath);
            byte[] fileBytes = Files.readAllBytes(path);
            String fileContent = new String(fileBytes, StandardCharsets.UTF_8);
            contentBuilder.append(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    public static String myReadFileToString(String filepath){
        StringBuilder s = new StringBuilder();
        try{
            Path path = Paths.get(filepath);
            byte[] fileBytes = Files.readAllBytes(path);
            String str = new String(fileBytes, StandardCharsets.UTF_8);
            s.append(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(readAsString("file/testfile.txt"));
    }
}
