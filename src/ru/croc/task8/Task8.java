package ru.croc.task8;

import java.io.FileReader;
import java.io.IOException;

public class Task8 {
    public static boolean isSplitter(int c) {
        return c == ' ' || c == '\n';
    }

    public static void main(String[] args) throws IOException {
        try(FileReader reader = new FileReader(args[0]))
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

            System.out.print(count);
        }
    }
}
