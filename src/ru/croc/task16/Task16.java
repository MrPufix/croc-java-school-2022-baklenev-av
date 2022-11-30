package ru.croc.task16;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Task16 {

    public static void outFile(File file) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    public static void mergeFiles(File file1, File file2, File resultFile) throws FileNotFoundException {
        try (
                Scanner file1Scanner = new Scanner(file1);
                Scanner file2Scanner = new Scanner(file2);
                PrintWriter out = new PrintWriter(resultFile);
        ){
            boolean printed = false;
            String firstLine = "";
            String secondLine = "";
            while (file1Scanner.hasNextLine() || file2Scanner.hasNextLine() ||
                    !firstLine.isEmpty() || !secondLine.isEmpty()) {
                String outLine = "";

                if(file1Scanner.hasNextLine() && firstLine.isEmpty())
                    firstLine = file1Scanner.nextLine();
                if(file2Scanner.hasNextLine() && secondLine.isEmpty())
                    secondLine = file2Scanner.nextLine();

                if(!firstLine.isEmpty() && !secondLine.isEmpty())
                {
                    long time1 = Long.parseLong(firstLine.split(" ")[0]);
                    long time2 = Long.parseLong(secondLine.split(" ")[0]);
                    if(time1 > time2) {
                        outLine = secondLine;
                        secondLine = "";
                    } else {
                        outLine = firstLine;
                        firstLine = "";
                    }
                } else if(!firstLine.isEmpty()) {
                    outLine = firstLine;
                    firstLine = "";
                } else if (!secondLine.isEmpty()) {
                    outLine = secondLine;
                    secondLine = "";
                }

                if(printed) out.print('\n');
                out.print(outLine);
                printed = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File[] logs = new File(args[0]).listFiles(((dir, name) -> name.contains(".log") || name.contains(".trace")));
        if(logs == null || logs.length == 0) return;
        if(logs.length == 1) {
            outFile(logs[0]);
            return;
        }
        System.out.println(Arrays.toString(logs));

        Path tempFolder = Paths.get(args[0]+"/temp");
        Files.createDirectories(tempFolder);

        File tempFile1 = new File(tempFolder + "/temp1.txt");
        tempFile1.createNewFile();
        File tempFile2 = new File(tempFolder + "/temp2.txt");
        tempFile2.createNewFile();

        int i;
        mergeFiles(logs[0], logs[1], tempFile1);
        for(i = 2; i < logs.length; i++) {
            if(i % 2 == 0) {
                mergeFiles(logs[i], tempFile1, tempFile2);
            } else {
                mergeFiles(logs[i], tempFile2, tempFile1);
            }
        }

        if(i % 2 == 0) {
            outFile(tempFile1);
        } else {
            outFile(tempFile2);
        }

        Files.deleteIfExists(tempFile1.toPath());
        Files.deleteIfExists(tempFile2.toPath());
        Files.deleteIfExists(tempFolder);
    }
}
