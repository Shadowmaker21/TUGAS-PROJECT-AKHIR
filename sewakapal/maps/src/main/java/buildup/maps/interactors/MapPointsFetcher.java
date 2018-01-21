package buildup.maps.interactors;

import java.util.List;

import buildup.ds.Datasource;
import buildup.ds.GeoDatasource;
import buildup.ds.Pagination;
import buildup.ds.SearchOptions;
import buildup.ds.restds.AppNowDatasource;
import buildup.ds.restds.GeoPoint;
import buildup.maps.ds.GeoFilter;

public class MapPointsFetcher<T> {

    private final Datasource<T> datasource;
    private final String field;

    public MapPointsFetcher(Datasource<T> ds, String field) {
        datasource = ds;
        this.field = field;
    }

    public void fetch(GeoPoint sw, GeoPoint nE, Datasource.Listener<List<T>> listener) {
        GeoFilter filter = new GeoFilter(field, sw, nE);

        fetchAddingFilter(listener, filter);
    }

    public void fetch(GeoPoint center, long distance, Datasource.Listener<List<T>> listener) {
        fetchAddingFilter(listener, new GeoFilter(field, center, distance));
    }

    private void fetchAddingFilter(Datasource.Listener<List<T>> listener, GeoFilter filter) {
        if (!(datasource instanceof GeoDatasource)) {
            return;
        }
        datasource.clearFilters();
        datasource.addFilter(filter);

        if (datasource instanceof AppNowDatasource) {
            AppNowDatasource<T> ands = (AppNowDatasource<T>) datasource;
            ands.getItems(listener);
        } else if (datasource instanceof Pagination) {
            Pagination<T> paginatedDs = (Pagination<T>) datasource;
            paginatedDs.getItems(0, listener);
        }

    }
}
