package buildup.mvp.presenter;

import buildup.mvp.presenter.Presenter;

public interface DetailCrudPresenter<T> extends Presenter {
    /**
     * delete item
     *
     * @param item
     */
    void deleteItem(T item);

    /**
     * Go to edit form
     *
     * @param item
     */
    void editForm(T item);

}
