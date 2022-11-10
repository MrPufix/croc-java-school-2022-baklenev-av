package ru.croc.task8;

import java.io.FileReader;
import java.io.IOException;

public class Task8 {
    public static boolean isSplitter(int c) {
        return c == ' ' || c == '\n';
    }

    /**
     * Counts words in file.
     * A sequence of alphabetic characters
     * separated from the sides by spaces or \n is considered a word.
     * @param fileName file to count words in
     * @return amount of words in file
     * @throws IOException if reader cannot open file for some reason
     */
    public static int countWordsInFile(String fileName) throws IOException {
        try(FileReader reader = new FileReader(fileName))
        {
            int count = 0;

            int c;
            boolean readingWord = false;
            while((c=reader.read())!=-1){
                if(!readingWord && Character.isAlphabetic(c)) {
                    readingWord = true;
                }
                else if(readingWord && isSplitter(c)) {
                    readingWord = false;
                    count++;
                }
            }

            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print(countWordsInFile(args[0]));
    }
}
