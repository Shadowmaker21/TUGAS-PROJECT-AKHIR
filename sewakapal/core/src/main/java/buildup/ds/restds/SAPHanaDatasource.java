/*
 * Copyright (c) 2015.
 * This code is part of Buildup (http://www.buildup.io)
 */

package buildup.ds.restds;

import java.net.URL;
import java.util.List;

import buildup.ds.Datasource;
import buildup.ds.Pagination;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;

/**
 * SAP Hana Datasource
 */
public abstract class SAPHanaDatasource<T> implements Datasource<T>, Pagination<T> {

    protected SearchOptions searchOptions;

    public SAPHanaDatasource(SearchOptions searchOptions) {
        this.searchOptions = searchOptions;
    }

    protected String getSort(SearchOptions options){
        if(options == null)
            return null;

        String col = options.getSortColumn();
        boolean asc = options.isSortAscending();

        if(col == null)
            return null;

        if (!asc)
            col += " desc";
        else
            col += " asc";

        return col;
    }

    // search (without pagination)
    public abstract void getItems(SearchOptions options, Listener<List<T>> listener);

    /**
     * Get the url for a image resource in this datasource
     * @param path the image path (can be relative or absolute)
     * @return the URL object you can pass to an ImageLoader class
     */
    public abstract URL getImageUrl(String path);

    public void onSearchTextChanged(String s) {}

    public void addFilter(Filter filter) {}

    public void clearFilters() {}
}
