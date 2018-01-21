
package us.qampus.sewakapal.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import buildup.ui.DrawerActivity;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;
import us.qampus.sewakapal.R;

public class SewaKapalMainActivity extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
            sectionFragments.append(R.id.entry0, SewaKapalFragment.class);
            sectionFragments.append(R.id.entry1, LogoutFragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}
