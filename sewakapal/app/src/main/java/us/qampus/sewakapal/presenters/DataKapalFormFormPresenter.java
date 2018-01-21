
package us.qampus.sewakapal.presenters;

import us.qampus.sewakapal.R;
import us.qampus.sewakapal.ds.SewaKapalDSItem;

import java.util.List;

import buildup.ds.CrudDatasource;
import buildup.ds.Datasource;
import buildup.mvp.presenter.BaseFormPresenter;
import buildup.mvp.view.FormView;

public class DataKapalFormFormPresenter extends BaseFormPresenter<SewaKapalDSItem> {

    private final CrudDatasource<SewaKapalDSItem> datasource;

    public DataKapalFormFormPresenter(CrudDatasource<SewaKapalDSItem> datasource, FormView<SewaKapalDSItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(SewaKapalDSItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(SewaKapalDSItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(SewaKapalDSItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(SewaKapalDSItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(SewaKapalDSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(SewaKapalDSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<SewaKapalDSItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}
