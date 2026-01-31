package com.sms.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.function.Predicate;

/**
 * SearchFilterUtil
 *
 * Utility class to filter ObservableLists based on a search query.
 */
public class SearchFilterUtil {

    /**
     * Filters an ObservableList based on a search query.
     *
     * @param originalList Original ObservableList
     * @param query        Search text (case-insensitive)
     * @param <T>          Type of objects in the list
     * @param predicate    Predicate to check if object matches query
     * @return Filtered ObservableList
     */
    public static <T> ObservableList<T> filterList(ObservableList<T> originalList,
                                                    String query,
                                                    Predicate<T> predicate) {
        if (query == null || query.isEmpty()) {
            return originalList; // no filter
        }

        String lowerCaseQuery = query.toLowerCase();

        FilteredList<T> filteredList = new FilteredList<>(originalList, item -> predicate.test(item));

        filteredList.setPredicate(item -> predicate.test(item) &&
                item.toString().toLowerCase().contains(lowerCaseQuery));

        return FXCollections.observableArrayList(filteredList);
    }
}


