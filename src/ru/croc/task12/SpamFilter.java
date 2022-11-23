package ru.croc.task12;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamFilter implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        Iterator<String> i = comments.iterator();
        while (i.hasNext()){
            String comment = i.next();
            for (String blackListedWord : blackList) {
                String lowerCaseComment = comment.toLowerCase();
                String lowerCaseBLWord = blackListedWord.toLowerCase();
                if (sentenceContainsWord(lowerCaseComment, lowerCaseBLWord))
                {
                    i.remove();
                    break;
                }
            }
        }
    }

    /**
     * Checks if sentence contains word.
     * Does not count if word is a part of other word.
     * All characters in a sentence and in a word must be in the same case.
     * @param sentence to check
     * @param word to look for in sentence
     * @return true if sentence contains word
     */
    private boolean sentenceContainsWord(String sentence, String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher match = pattern.matcher(sentence);

        while (match.find()) {
            int indexBefore = match.start() - 1;
            int indexAfter = match.end();

            char charBefore = indexBefore >= 0 ? sentence.charAt(indexBefore) : '-';
            char charAfter = indexAfter < sentence.length()  ? sentence.charAt(indexAfter) : '-';

            if(!Character.isAlphabetic(charBefore) && !Character.isAlphabetic(charAfter)) return true;
        }
        return false;
    }
}
