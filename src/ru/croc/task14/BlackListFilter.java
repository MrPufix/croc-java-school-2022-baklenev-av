package ru.croc.task14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public interface BlackListFilter {
    /**
     * Filters comments based on given predicate.
     * @param comments iterable collection of comments to filter
     * @param isBlacklisted predicate to test if comment is blacklisted
     * @return collection of comments without blacklisted comments
     * @param <T> class representing comment
     */
    default <T> Collection<T> filterComments(Iterable<T> comments, Predicate<T> isBlacklisted) {
        Collection<T> result = new ArrayList<>();
        for (T comment : comments) {
            if (!isBlacklisted.test(comment))
                result.add(comment);
        }
        return result;
    }
}


