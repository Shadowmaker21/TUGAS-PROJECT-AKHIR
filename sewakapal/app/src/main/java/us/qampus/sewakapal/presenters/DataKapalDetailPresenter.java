
package us.qampus.sewakapal.presenters;

import us.qampus.sewakapal.R;
import us.qampus.sewakapal.ds.SewaKapalDSItem;

import buildup.ds.CrudDatasource;
import buildup.ds.Datasource;
import buildup.mvp.presenter.BasePresenter;
import buildup.mvp.presenter.DetailCrudPresenter;
import buildup.mvp.view.DetailView;

public class DataKapalDetailPresenter extends BasePresenter implements DetailCrudPresenter<SewaKapalDSItem>,
      Datasource.Listener<SewaKapalDSItem> {

    private final CrudDatasource<SewaKapalDSItem> datasource;
    private final DetailView view;

    public DataKapalDetailPresenter(CrudDatasource<SewaKapalDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(SewaKapalDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(SewaKapalDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(SewaKapalDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}
