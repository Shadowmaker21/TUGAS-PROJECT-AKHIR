package us.qampus.sewakapal.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.behaviors.FabBehaviour;
import buildup.behaviors.SearchBehavior;
import buildup.behaviors.SelectionBehavior;
import buildup.ds.Datasource;
import buildup.ds.restds.AppNowDatasource;
import buildup.ui.ListGridFragment;
import buildup.util.Constants;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import buildup.util.ViewHolder;
import java.net.URL;
import java.util.List;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import us.qampus.sewakapal.ds.SewaKapalDSService;
import us.qampus.sewakapal.presenters.DataKapalPresenter;
import us.qampus.sewakapal.R;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import us.qampus.sewakapal.ds.SewaKapalDSItem;
import us.qampus.sewakapal.ds.SewaKapalDS;
import buildup.mvp.view.CrudListView;
import buildup.ds.CrudDatasource;
import buildup.dialogs.DeleteItemDialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import buildup.util.Constants;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "DataKapalFragment" listing
 */
public class DataKapalFragment extends ListGridFragment<SewaKapalDSItem> implements CrudListView<SewaKapalDSItem>, DeleteItemDialog.DeleteItemListener {

    private CrudDatasource<SewaKapalDSItem> datasource;
    private List<SewaKapalDSItem> selectedItemsForDelete;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static DataKapalFragment newInstance(Bundle args) {
        DataKapalFragment fr = new DataKapalFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new DataKapalPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        addBehavior(new SearchBehavior(this));
        // Multiple selection
        SelectionBehavior<SewaKapalDSItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<SewaKapalDSItem>() {
            @Override
            public void onSelected(List<SewaKapalDSItem> selectedItems) {
                selectedItemsForDelete = selectedItems;
                DialogFragment deleteDialog = new DeleteItemDialog(DataKapalFragment.this);
                deleteDialog.show(getActivity().getSupportFragmentManager(), "");
            }
        });
        addBehavior(selectionBehavior);

        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
        
    }

    protected SearchOptions getSearchOptions() {
        SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.datakapal_item;
    }

    @Override
    protected Datasource<SewaKapalDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = SewaKapalDS.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(SewaKapalDSItem item, View view, int position) {
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext(), false);
        ImageView image = ViewHolder.get(view, R.id.image);
        URL imageMedia = ((AppNowDatasource) getDatasource()).getImageUrl(item.image);
        if(imageMedia != null){
            imageLoader.load(imageLoaderRequest()
                          .withPath(imageMedia.toExternalForm())
                          .withTargetView(image)
                          .fit()
        				  .build()
            );
        	
        }
        else {
            imageLoader.load(imageLoaderRequest()
                          .withResourceToLoad(R.drawable.ic_ibm_placeholder)
                          .withTargetView(image)
        				  .build()
            );
        }
        
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        title.setText((item.name != null ? item.name : ""));
        
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        subtitle.setText((item.telp != null ? item.telp : ""));
        
    }

    @Override
    protected void itemClicked(final SewaKapalDSItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(SewaKapalDSItem item, int position) {
        Intent intent = generateIntentToDetailOrForm(item,
            position,
            getActivity(),
            DataKapalDetailActivity.class);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showAdd() {
        startActivityForResult(
                generateIntentToDetailOrForm(null,
                        0,
                        getActivity(),
                        DataKapalFormFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(SewaKapalDSItem item, int position) {
        startActivityForResult(
                generateIntentToDetailOrForm(item,
                        position,
                        getActivity(),
                        DataKapalFormFormActivity.class
                ), Constants.MODE_EDIT
        );
    }

    @Override
    public void deleteItem(boolean isDeleted) {
        if (isDeleted) {
            getPresenter().deleteItems(selectedItemsForDelete);
        }

        selectedItemsForDelete.clear();
    }

}
