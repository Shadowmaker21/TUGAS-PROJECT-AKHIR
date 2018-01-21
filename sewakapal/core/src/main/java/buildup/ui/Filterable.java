/*
 * Copyright 2016.
 * This code is part of Buildup
 */

package buildup.ui;

import buildup.ds.filter.Filter;

/**
 * UI component (activity or fragment) that supports search operations
 */
public interface Filterable {

    /**
     * Set the search parameter
     */
    void onSearchTextChanged(String s);

    /**
     * filters
     *
     * @param filter
     */
    void addFilter(Filter filter);

    void clearFilters();

}
