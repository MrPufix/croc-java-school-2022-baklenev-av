package ru.croc.task12;

import java.util.*;

public class Task12 {
    public static void main(String[] args) {
        ArrayList<String> comments = new ArrayList<>(List.of(
                "spam",
                "Good comment, with some words in it. And other stuff. NotSpamWord",
                "Other comment, badword",
                "Comment with, Spam!",
                "Another comment, about notspam things",
                "Spam comment",
                "Spam",
                "Things... Defgfergdgsdgr, ffewdsf",
                "Big comment, with a lot of words. Containing spam.",
                "Badword, Spam",
                "Dsdweaferwgdjskgljs sfafekokpkpkBADWORDsaferwjgp",
                "TODELETE fasfkre cx. fase, fdfrerfg e."
        ));

        Set<String> blackList = new HashSet<>(List.of("spam", "ToDelete", "badword"));

        BlackListFilter spamFilter = new SpamFilter();

        spamFilter.filterComments(comments, blackList);

        System.out.println(comments);
    }
}
