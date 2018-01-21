package buildup.mvp.presenter;

import java.util.List;

import buildup.mvp.presenter.Presenter;

public interface ListCrudPresenter<T> extends Presenter {
    /**
     * delete one item
     * @param item
     */
    void deleteItem(T item);

    /**
     * delete a list of items
     * @param items
     */
    void deleteItems(List<T> items);

    /**
     * Go to create form
     */
    void addForm();

    /**
     * Go to edit form
     * @param item
     */
    void editForm(T item, int position);

    /**
     * Go to detail
     * @param item
     * @param position
     */
    void detail(T item, int position);
}
