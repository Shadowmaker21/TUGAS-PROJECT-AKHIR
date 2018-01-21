package buildup.data;

import buildup.ds.SearchOptions;

public interface DatasourceQuery<T> {
    T generateQuery(SearchOptions searchOptions);
}
