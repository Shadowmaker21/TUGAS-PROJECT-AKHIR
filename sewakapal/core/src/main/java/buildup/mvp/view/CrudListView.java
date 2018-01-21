package buildup.mvp.view;

public interface CrudListView<T> extends ListView<T> {
    void showAdd();
    void showEdit(T item, int position);
}
