package tutorial.com.felix.manyfeatures;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    private static final String DEF_PREFERENCES_NAME = "my_pref";
    private static final String KEY_DRAWER_LEARNED = "key_drawer_learned";

    private RecyclerView recyclerView;
    private DrawerItemAdapter drawerItemAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mDrawerLearned;
    private boolean mFromOtherState;
    private View view;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLearned = Boolean.valueOf(readPreferences(getActivity(), KEY_DRAWER_LEARNED, "false"));
        if (savedInstanceState != null) {
            mFromOtherState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawer_list);
        drawerItemAdapter = new DrawerItemAdapter(getActivity(), initDrawerItem());
        recyclerView.setAdapter(drawerItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setUp(int viewId, DrawerLayout layout, final Toolbar toolbar) {
        view = getActivity().findViewById(viewId);
        mDrawerLayout = layout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), layout, toolbar,
                R.string.drawer_open, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mDrawerLearned) {
                    mDrawerLearned = true;
                    savePreferences(getActivity(), KEY_DRAWER_LEARNED, String.valueOf(mDrawerLearned));
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if (!mDrawerLearned && !mFromOtherState) {
            mDrawerLayout.openDrawer(view);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void savePreferences(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(DEF_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readPreferences(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(DEF_PREFERENCES_NAME, context.MODE_PRIVATE);
        String result = preferences.getString(key, defaultValue);
        return result;
    }

    public static List<DrawerItem> initDrawerItem() {
        List<DrawerItem> items = new ArrayList<DrawerItem>();
        int[] iconList = {R.drawable.ic_favorite24, R.drawable.ic_gps_fixed24,
                R.drawable.ic_share24, R.drawable.ic_settings24};
        String[] titleList = {"Like", "Location", "Share", "Settings"};
        String[] subTitleList = {"love anything you have", "get your location",
                "share to entire world", "personalize as you want"};

        for (int i = 0; i < titleList.length; i++) {
            DrawerItem item = new DrawerItem();
            item.setIconId(iconList[i]);
            item.setTitle(titleList[i]);
            item.setSubTitle(subTitleList[i]);
            items.add(item);
        }

        return items;
    }
}
