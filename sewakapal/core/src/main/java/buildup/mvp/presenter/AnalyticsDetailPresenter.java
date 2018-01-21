package buildup.mvp.presenter;

import buildup.ds.CrudDatasource;
import buildup.mvp.view.DetailView;

public class AnalyticsDetailPresenter<T> extends DetailPresenter<T> {

    public AnalyticsDetailPresenter(CrudDatasource<T> datasource, DetailView view) {
        super(datasource, view);
    }


}
