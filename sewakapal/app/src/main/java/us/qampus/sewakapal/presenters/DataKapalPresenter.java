
package us.qampus.sewakapal.presenters;

import us.qampus.sewakapal.R;
import us.qampus.sewakapal.ds.SewaKapalDSItem;

import java.util.List;

import buildup.ds.CrudDatasource;
import buildup.ds.Datasource;
import buildup.mvp.presenter.BasePresenter;
import buildup.mvp.presenter.ListCrudPresenter;
import buildup.mvp.view.CrudListView;

public class DataKapalPresenter extends BasePresenter implements ListCrudPresenter<SewaKapalDSItem>,
      Datasource.Listener<SewaKapalDSItem>{

    private final CrudDatasource<SewaKapalDSItem> crudDatasource;
    private final CrudListView<SewaKapalDSItem> view;

    public DataKapalPresenter(CrudDatasource<SewaKapalDSItem> crudDatasource,
                                         CrudListView<SewaKapalDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(SewaKapalDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<SewaKapalDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(SewaKapalDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(SewaKapalDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(SewaKapalDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}
