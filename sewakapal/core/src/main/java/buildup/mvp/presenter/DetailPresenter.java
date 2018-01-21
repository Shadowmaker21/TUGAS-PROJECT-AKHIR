package buildup.mvp.presenter;

import buildup.core.R;
import buildup.ds.CrudDatasource;
import buildup.ds.Datasource;
import buildup.mvp.view.DetailView;

public class DetailPresenter<T> extends BasePresenter implements DetailCrudPresenter<T>, Datasource.Listener<T> {

    private final CrudDatasource<T> datasource;
    private final DetailView view;

    //Add datasource
    public DetailPresenter(CrudDatasource<T> datasource, DetailView view) {
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(T item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(T item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(T item) {
        view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}
