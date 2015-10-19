package tutorial.com.felix.manyfeatures;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    private static final String DEF_PREFERENCES_NAME = "my_pref";
    private static final String KEY_DRAWER_LEARNED = "key_drawer_learned";

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
        if (savedInstanceState != null){
            mFromOtherState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void setUp(int viewId, DrawerLayout layout, final Toolbar toolbar){
        view = getActivity().findViewById(viewId);
        mDrawerLayout = layout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), layout, toolbar,
                R.string.drawer_open, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mDrawerLearned){
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

        if(!mDrawerLearned && !mFromOtherState){
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

    public static void savePreferences(Context context, String key, String value){
        SharedPreferences preferences = context.getSharedPreferences(DEF_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readPreferences(Context context, String key, String defaultValue){
        SharedPreferences preferences = context.getSharedPreferences(DEF_PREFERENCES_NAME, context.MODE_PRIVATE);
        String result = preferences.getString(key, defaultValue);
        return result;
    }
}
