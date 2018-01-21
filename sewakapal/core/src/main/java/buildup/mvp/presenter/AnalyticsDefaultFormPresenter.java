package buildup.mvp.presenter;

import buildup.ds.CrudDatasource;
import buildup.mvp.view.FormView;

public class AnalyticsDefaultFormPresenter<T> extends DefaultFormPresenter<T> {

    public AnalyticsDefaultFormPresenter(CrudDatasource<T> datasource, FormView<T> view) {
        super(datasource, view);
    }
}
