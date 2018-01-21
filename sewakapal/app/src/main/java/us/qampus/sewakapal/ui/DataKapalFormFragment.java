
package us.qampus.sewakapal.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import buildup.ds.restds.GeoPoint;
import buildup.ui.FormFragment;
import buildup.util.StringUtils;
import buildup.views.GeoPicker;
import buildup.views.ImagePicker;
import buildup.views.TextWatcherAdapter;
import java.io.IOException;
import us.qampus.sewakapal.ds.SewaKapalDSItem;
import us.qampus.sewakapal.ds.SewaKapalDSService;
import us.qampus.sewakapal.presenters.DataKapalFormFormPresenter;
import us.qampus.sewakapal.R;
import java.io.File;

import static android.net.Uri.fromFile;
import buildup.ds.Datasource;
import buildup.ds.CrudDatasource;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import us.qampus.sewakapal.ds.SewaKapalDSItem;
import us.qampus.sewakapal.ds.SewaKapalDS;

public class DataKapalFormFragment extends FormFragment<SewaKapalDSItem> {

    private CrudDatasource<SewaKapalDSItem> datasource;
    public static DataKapalFormFragment newInstance(Bundle args){
        DataKapalFormFragment fr = new DataKapalFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public DataKapalFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new DataKapalFormFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

    }

    @Override
    protected SewaKapalDSItem newItem() {
        SewaKapalDSItem newItem = new SewaKapalDSItem();
        return newItem;
    }

    private SewaKapalDSService getRestService(){
        return SewaKapalDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.datakapalform_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final SewaKapalDSItem item, View view) {
        
        bindString(R.id.sewakapalds_name, item.name, false, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.name = s.toString();
            }
        });
        
        
        bindString(R.id.sewakapalds_telp, item.telp, false, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.telp = s.toString();
            }
        });
        
        
        bindString(R.id.sewakapalds_pengguna, item.pengguna, false, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.pengguna = s.toString();
            }
        });
        
        
        bindImage(R.id.sewakapalds_image,
            item.image != null ?
                getRestService().getImageUrl(item.image) : null,
            false,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.image = null;
                    item.imageUri = null;
                    ((ImagePicker) getView().findViewById(R.id.sewakapalds_image)).clear();
                }
            }
        );
        
        
        bindLocation(R.id.sewakapalds_location, item.location, false,
            new GeoPicker.PointChangedListener() {
                @Override
                public void onPointChanged(GeoPoint point) {
                    item.location = point;
                }
            }
        );
        
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            SewaKapalDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
                imageUri = data.getData();
                switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // image field
                            item.imageUri = imageUri;
                            item.image = "cid:image";
                            picker = (ImagePicker) getView().findViewById(R.id.sewakapalds_image);
                            break;
                        
                    default:
                        return;
                }

                picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
                switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // image field
                            picker = (ImagePicker) getView().findViewById(R.id.sewakapalds_image);
                            imageUri = fromFile(picker.getImageFile());
                        		item.imageUri = imageUri;
                            item.image = "cid:image";
                            break;
                        
                    default:
                        return;
                }
                picker.setImageUri(imageUri);
            }
        }
    }
}
