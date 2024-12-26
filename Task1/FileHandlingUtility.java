package Task1;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileHandlingUtility {

    // Method to create a file

    public static boolean createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
                return true;
            } else {
                System.out.println("File already exists: " + filePath);
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
            return false;
        }
    }

    // Method to write data to a file (overwrites the file)

    public static void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Method to append data to a file

    public static void appendToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.append(content);
            System.out.println("Data appended to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file.");
            e.printStackTrace();
        }
    }

    // Method to read content from a file

    public static void readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    // Method to delete a file

    public static boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.deleteIfExists(path)) {
                System.out.println("File deleted: " + filePath);
                return true;
            } else {
                System.out.println("File not found: " + filePath);
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the file.");
            e.printStackTrace();
            return false;
        }
    }

    // Method to copy a file

    public static void copyFile(String sourcePath, String destinationPath) {
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destinationPath);
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied from " + sourcePath + " to " + destinationPath);
        } catch (IOException e) {
            System.out.println("An error occurred while copying the file.");
            e.printStackTrace();
        }
    }

    // Method to move a file

    public static void moveFile(String sourcePath, String destinationPath) {
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destinationPath);
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved from " + sourcePath + " to " + destinationPath);
        } catch (IOException e) {
            System.out.println("An error occurred while moving the file.");
            e.printStackTrace();
        }
    }

    // Method to list all files in a directory

    public static void listFilesInDirectory(String directoryPath) {
        try {
            Files.list(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            System.out.println("An error occurred while listing files in the directory.");
            e.printStackTrace();
        }
    }

    // Method to read all lines from a file (using NIO)

    public static void readAllLines(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    // Method to write data to a file using NIO

    public static void writeToFileNIO(String filePath, String content) {
        try {
            Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("Data written to file using NIO.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Method to check if a file exists

    public static boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    public static void main(String[] args) {
        String filePath = "testfile.txt";
        String dirPath = ".";

        // Example usage of file operations
        
        createFile(filePath);
        writeToFile(filePath, "Hello, this is a test file.");
        appendToFile(filePath, "\nAppended data.");
        readFromFile(filePath);
        copyFile(filePath, "copied_testfile.txt");
        moveFile(filePath, "moved_testfile.txt");
        listFilesInDirectory(dirPath);
        deleteFile("moved_testfile.txt");
    }
}

