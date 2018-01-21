package us.qampus.sewakapal.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.actions.StartActivityAction;
import buildup.ds.Datasource;
import buildup.ds.restds.AppNowDatasource;
import buildup.ds.restds.GeoPoint;
import buildup.util.Constants;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.net.URL;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import us.qampus.sewakapal.ds.SewaKapalDSItem;
import us.qampus.sewakapal.ds.SewaKapalDS;
import buildup.maps.presenters.MapPresenter;

/**
 * "MapsFragment" listing
 */
public class MapsFragment extends buildup.maps.ui.MapFragment<SewaKapalDSItem> {
    private Datasource<SewaKapalDSItem> datasource;
    private SearchOptions searchOptions;

    public static MapsFragment newInstance(Bundle args){
        MapsFragment fr = new MapsFragment();
        fr.setArguments(args);

        return fr;
    }

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		mPresenter = new MapPresenter<>(getDatasource(), getLocationField(), this);
    }

	  @Override
    protected Datasource<SewaKapalDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        searchOptions = SearchOptions.Builder.searchOptions().build();
        datasource = SewaKapalDS.getInstance(searchOptions);
        return datasource;
    }

    @Override
    protected int getMapType() {
        return GoogleMap.MAP_TYPE_TERRAIN;
    }

    @Override
    protected String getLocationField() {
        return "location";
    }

    @Override
    protected Marker createAndBindMarker(GoogleMap map, SewaKapalDSItem item) {
        Marker marker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(getLocationForItem(item).coordinates[1],
                                        getLocationForItem(item).coordinates[0])));
        // Binding
        marker.setTitle((item.name != null ? item.name : ""));
        return marker;
    }


    protected GeoPoint getLocationForItem(SewaKapalDSItem item) {
        return item.location;
    }

    @Override
    public void navigateToDetail(SewaKapalDSItem item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CONTENT, item);
        new StartActivityAction(MapsDetailActivity.class, bundle).execute(getActivity());
    }
}

