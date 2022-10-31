package ru.croc.task4;

public class Task4 {
    public  static String removeJavaComments(String original) {
        String noComments = "";

        boolean erase = false;
        char stopChar = '\n';
        int commentStartIndex = 0;
        for (int i = 0; i < original.length(); i++) {
            // if found comment end
            if(erase && original.charAt(i) == stopChar) {
                if(stopChar == '\n') {
                    erase = false;
                    noComments += '\n';
                    continue;
                } else if (commentStartIndex != (i-1) && original.charAt(i-1) == '*') {
                    erase = false;
                    continue;
                }
            }

            // if found comment start
            if(!erase && original.charAt(i) == '/') {
                erase = true;

                char nextChar = original.charAt(i+1);
                commentStartIndex = ++i;

                switch (nextChar) {
                    case ('*') -> stopChar = '/';
                    case ('/') -> stopChar = '\n';
                    default -> {
                        erase = false;
                        i--;
                    }
                }
            }

            if(!erase) {
                noComments += original.charAt(i);
            }
        }

        return noComments;
    }

    public static void main(String[] args) {
        String source = """
                /*/*
                 * My first ever program in Java! /
                 *///
                class Hello { ///*/* class body starts here*/
                
                  /* main method */
                  public static void main(String[] args/* we put command line arguments here*/) {
                    // this line prints my first greeting to the screen
                    System.out.println("Hi!"); // :)
                  }
                } // the end
                // to be continued...""";

        String noComments = removeJavaComments(source);

        System.out.print(noComments);
    }
}
