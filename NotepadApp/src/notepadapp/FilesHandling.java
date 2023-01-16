package notepadapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FilesHandling {

    public void saveFileForFirstTime(File file, String text) {
        try {
            file.createNewFile();
        } catch (IOException ex) {
        }
        saveFile(file, text);
    }

    public void saveFile(File file, String text) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file), true)) {
            writer.println(text);
        } catch (IOException ex) {
        }
    }

    public StringBuffer openFile(File file) {
        StringBuffer openedFileText = new StringBuffer();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                openedFileText.append(reader.nextLine()).append("\n");
            }
        } catch (FileNotFoundException ex) {
        }

        return openedFileText;
    }

    public void editSettingsFile(String path, String font, String size, String color) throws IOException {
        boolean fileFound = false;
        File file = new File("./src/files/settings.txt");
        File temp = new File("./src/files/temp.txt");
        temp.createNewFile();
        file.createNewFile();
        String line1, line2;
        try (BufferedReader reader = new BufferedReader(new FileReader(file)); 
                PrintWriter writer = new PrintWriter(new FileWriter(temp), true)) {
            
            while ((line1 = reader.readLine()) != null) {
                String[] str = line1.split("@");
                if (str[0].equals(path)) {                                          //if file path is the same of that line, update its edits
                    line2 = path + "@" + font + "@" + size + "@" + color;
                    writer.println(line2);
                    fileFound = true;
                } else {
                    writer.println(line1);                                          //if file path is different, rewrite same line
                }
            }
            if (!fileFound) {                                                       // if file path does not exist in settings file, add it
                line2 = path + "@" + font + "@" + size + "@" + color;
                writer.println(line2);
            }
            
        }
        file.delete();
        temp.renameTo(file);
    }

    public String[] getSettingsOfFile(File file) {
        String line;
        File settingsFile = new File("./src/files/settings.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(settingsFile));) {
            while ((line = reader.readLine()) != null) {
                String[] str = line.split("@");
                if (str[0].equals(file.getPath())) {
                    return str;
                }
            }
        } catch (IOException ex) {
        }
        return null;
    }

    public void renameDocInSettingsFile(File renamedFile, String name) throws IOException {
        File file = new File("./src/files/settings.txt");
        File temp = new File("./src/files/temp.txt");
        temp.createNewFile();
        file.createNewFile();
        String line1, line2;
        try (BufferedReader reader = new BufferedReader(new FileReader(file)); 
                PrintWriter writer = new PrintWriter(new FileWriter(temp), true)) {
            while ((line1 = reader.readLine()) != null) {
                String[] str = line1.split("@");
                if (str[0].equals(renamedFile.getPath())) {
                    line2 = renamedFile.getPath().replace(renamedFile.getName(), name) + ".txt" + "@" + str[1] + "@" + str[2] + "@" + str[3];
                    writer.println(line2);
                } else {
                    writer.println(line1);
                }
            }
        }
        file.delete();
        temp.renameTo(file);
    }

}
