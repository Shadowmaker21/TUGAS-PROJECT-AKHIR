package buildup.data;

import buildup.ds.SearchOptions;

public interface DatasourceSort<T> {

    T generateSort(SearchOptions searchOptions);
}
