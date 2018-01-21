

package us.qampus.sewakapal.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import us.qampus.sewakapal.R;

import buildup.ui.BaseListingActivity;
/**
 * SewaKapalActivity list activity
 */
public class SewaKapalActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.sewaKapalActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return SewaKapalFragment.class;
    }

}
