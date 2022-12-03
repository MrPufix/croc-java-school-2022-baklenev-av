package ru.croc.task13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FilmRecommendation {
    private final static String filmsFileName = "films.txt";
    private final static String historyFileName = "history.txt";

    private final HashMap<Integer, String> films;
    private final ArrayList<String> history;

    FilmRecommendation() throws FileNotFoundException{
        films = new HashMap<>();
        history = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(filmsFileName)))
        {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                int filmIndex = Integer.parseInt(line[0]);
                films.put(filmIndex, line[1]);
            }
        }

        try(Scanner scanner = new Scanner(new File(historyFileName)))
        {
            while (scanner.hasNextLine()) {
                history.add(scanner.nextLine());
            }
        }
    }

    /**
     * Recommends film based on input user history.
     * User history is a string containing integer film indexes separated by comma.
     * @param inputUserHistory history of user to recommend film for
     * @return name of film recommended for user
     */
    public String getRecommendation(String inputUserHistory) {
        Set<String> uniqueInputUserFilms = hashSetOf(inputUserHistory.split(","));

        //Step 1
        List<String> similarUserHistories = findSimilarUsers(uniqueInputUserFilms);

        //Step 2
        for(int i = 0; i < similarUserHistories.size(); i++) {
            String similarUserHistory = similarUserHistories.get(i);
            similarUserHistory = removeFilms(similarUserHistory, uniqueInputUserFilms);
            similarUserHistories.set(i, similarUserHistory);
        }

        //Step3
        Set<String> filmsViewedBySimilarUsers = getFilmsSet(similarUserHistories);
        int recommendedFilmIndex = getHighestViewedFilmIndex(filmsViewedBySimilarUsers);

        return films.get(recommendedFilmIndex);
    }

    /**
     * Finds users who have at least half of their
     * movies the same as the input user's movies.
     * Checks only unique movies.
     * User history is a string containing integer film indexes separated by comma.
     * @param uniqueUserFilms set of input user movies
     * @return array of histories of users matching the criteria
     */
    private ArrayList<String> findSimilarUsers(Set<String> uniqueUserFilms) {
        ArrayList<String> similarUsers = new ArrayList<>();

        for(String userHistory : history) {
            Set<String> uniqueFilms = hashSetOf(userHistory.split(","));

            int similarFilmsCount = 0;
            for(String film : uniqueFilms) {
                if(uniqueUserFilms.contains(film))
                    similarFilmsCount++;
            }

            if(similarFilmsCount*2 >= uniqueUserFilms.size())
                similarUsers.add(userHistory);
        }

        return similarUsers;
    }

    /**
     * Removes films from user history.
     * User history is a string containing integer film indexes separated by comma.
     * @param userHistory history to remove films from
     * @param filmsToRemove set of films to remove
     * @return userHistory without filmsToRemove
     */
    private String removeFilms(String userHistory, Set<String> filmsToRemove) {
        String[] userFilms = userHistory.split(",");
        StringBuilder res = new StringBuilder();

        for(String film : userFilms) {
            if(!filmsToRemove.contains(film)) {
                if(res.length() > 0) res.append(',');
                res.append(film);
            }
        }

        return res.toString();
    }

    /**
     * Get set of films from list of user histories
     * @param userHistories - list of user histories
     * @return set of films
     */
    private Set<String> getFilmsSet(List<String> userHistories) {
        HashSet<String> result = new HashSet<>();
        for(String userHistory : userHistories) {
            result.addAll(Arrays.asList(userHistory.split(",")));
        }
        return result;
    }

    /**
     * Returns index of highest viewed film from given set.
     * @param filmsToCheck set of films to seek highest viewed from
     * @return index of highest viewed film
     */
    private int getHighestViewedFilmIndex(Set<String> filmsToCheck) {
        int maxCount = 0;
        int maxFilmIndex = 0;

        HashMap<String, Integer> filmsCount = new HashMap<>();

        for(String userHistory : history) {
            for(String film : userHistory.split(",")) {
                if (!filmsToCheck.contains(film)) continue;

                Integer filmCount = filmsCount.get(film);
                if(filmCount == null) filmCount = 0;

                filmCount++;

                if(filmCount > maxCount) {
                    maxCount = filmCount;
                    maxFilmIndex = Integer.parseInt(film);
                }
                filmsCount.put(film, filmCount);
            }
        }

        return maxFilmIndex;
    }

    @SafeVarargs
    private static <T> HashSet<T> hashSetOf(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
