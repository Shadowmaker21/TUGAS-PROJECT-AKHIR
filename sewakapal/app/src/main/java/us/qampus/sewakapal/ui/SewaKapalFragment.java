

package us.qampus.sewakapal.ui;

import android.os.Bundle;

import us.qampus.sewakapal.R;

import java.util.ArrayList;
import java.util.List;

import buildup.MenuItem;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;

/**
 * SewaKapalFragment menu fragment.
 */
public class SewaKapalFragment extends buildup.ui.MenuFragment {
    /**
     * Default constructor
     */
    public SewaKapalFragment(){
        super();
    }

    // Factory method
    public static SewaKapalFragment newInstance(Bundle args) {
        SewaKapalFragment fragment = new SewaKapalFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("Data Kapal")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(DataKapalActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Maps")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(MapsActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.sewakapal_item;
    }
}
