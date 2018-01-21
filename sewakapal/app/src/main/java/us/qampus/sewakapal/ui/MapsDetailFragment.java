
package us.qampus.sewakapal.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.behaviors.ShareBehavior;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import java.net.URL;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import us.qampus.sewakapal.R;
import buildup.ds.Datasource;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import us.qampus.sewakapal.ds.SewaKapalDSItem;
import us.qampus.sewakapal.ds.SewaKapalDS;

public class MapsDetailFragment extends buildup.ui.DetailFragment<SewaKapalDSItem> implements ShareBehavior.ShareListener {

    private Datasource<SewaKapalDSItem> datasource;
    public static MapsDetailFragment newInstance(Bundle args){
        MapsDetailFragment fr = new MapsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public MapsDetailFragment(){
        super();
    }

    @Override
    public Datasource<SewaKapalDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = SewaKapalDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.mapsdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final SewaKapalDSItem item, View view) {
        
        TextView view0 = (TextView) view.findViewById(R.id.view0);
        view0.setText((item.name != null ? item.name : ""));
        
        
        TextView view1 = (TextView) view.findViewById(R.id.view1);
        view1.setText((item.telp != null ? item.telp : ""));
        
        
        TextView view2 = (TextView) view.findViewById(R.id.view2);
        view2.setText((item.pengguna != null ? item.pengguna : ""));
        
        
        ImageView view3 = (ImageView) view.findViewById(R.id.view3);
        URL view3Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.image);
        if(view3Media != null){
            ImageLoader imageLoader = new PicassoImageLoader(view3.getContext(), false);
            imageLoader.load(imageLoaderRequest()
                                   .withPath(view3Media.toExternalForm())
                                   .withTargetView(view3)
                                   .fit()
                                   .build()
                    );
            
        } else {
            view3.setImageDrawable(null);
        }
    }

    @Override
    protected void onShow(SewaKapalDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(String.format("Sewa Kapal %s", item.name));
    }
    @Override
    public void onShare() {
        SewaKapalDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.name != null ? item.name : "") + "\n" +
                    (item.telp != null ? item.telp : "") + "\n" +
                    (item.pengguna != null ? item.pengguna : ""));
        intent.putExtra(Intent.EXTRA_SUBJECT, String.format("Sewa Kapal %s", item.name));

        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}
