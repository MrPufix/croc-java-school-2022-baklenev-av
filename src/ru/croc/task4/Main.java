package ru.croc.task4;

public class Main {
    public  static String removeJavaComments(String original) {
        String noComments = "";

        boolean erase = false;
        char stopChar = '\n';
        for (int i = 0; i < original.length(); i++) {
            if(erase && original.charAt(i) == stopChar) {
                erase = false;
                if(stopChar == '\n')
                    noComments += '\n';
                continue;
            }

            if(!erase && original.charAt(i) == '/') {
                erase = true;
                stopChar = original.charAt(i+1) == '*' ? '/' : '\n';
            }

            if(!erase) {
                noComments += original.charAt(i);
            }
        }

        return noComments;
    }

    public static void main(String[] args) {
        String source = """
                /*
                 * My first ever program in Java!
                 */
                class Hello { // class body starts here
                
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
