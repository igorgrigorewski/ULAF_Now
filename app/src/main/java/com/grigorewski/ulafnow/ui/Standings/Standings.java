package com.grigorewski.ulafnow.ui.Standings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;

import com.grigorewski.ulafnow.R;
import com.grigorewski.ulafnow.api.response.Response;
import com.grigorewski.ulafnow.content.Team;
import com.grigorewski.ulafnow.loaders.StandingsLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Standings extends Fragment
        implements LoaderManager.LoaderCallbacks<Response>{

    List<Team> teams;
    List<String> afcDivisions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.standings, container, false);

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("AFC"));
        tabLayout.addTab(tabLayout.newTab().setText("NFC"));
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.standings_view_pager);

        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(R.id.standings_loader, Bundle.EMPTY, this);
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.standings_loader:
                return new StandingsLoader(getActivity());
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();
        if (id == R.id.standings_loader) {
            teams = data.getTypedAnswer();

            String[] groupFrom = {"standings_division_name"};

            int[] groupTo = {R.id.standings_division_name};

            String[] childFrom = {
                    "id",
                    //"logo",
                    "name",
                    "w",
                    "l",
                    "t",
                    "percent"
            };

            int[] childTo = {
                    R.id.standing_list_item_id,
                    //R.id.standing_list_item_logo,
                    R.id.standing_list_item_name,
                    R.id.standing_list_item_w,
                    R.id.standing_list_item_l,
                    R.id.standing_list_item_t,
                    R.id.standing_list_item_percent
            };

            ArrayList<HashMap<String, Object>> afc_groupData = new ArrayList<>();
            ArrayList<HashMap<String, Object>> nfc_groupData = new ArrayList<>();

            for (String division :
                    Arrays.asList("AFC EAST", "AFC NORTH", "AFC SOUTH", "AFC WEST")) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("standings_division_name", division);
                afc_groupData.add(map);
            }

            for (String division :
                    Arrays.asList("NFC EAST", "NFC NORTH", "NFC SOUTH", "NFC WEST")) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("standings_division_name", division);
                nfc_groupData.add(map);
            }

            ArrayList<ArrayList<HashMap<String, Object>>> afc_childData = new ArrayList<>();
            ArrayList<HashMap<String, Object>> afc_east_data = new ArrayList<>();
            ArrayList<HashMap<String, Object>> afc_north_data = new ArrayList<>();
            ArrayList<HashMap<String, Object>> afc_south_data = new ArrayList<>();
            ArrayList<HashMap<String, Object>> afc_west_data = new ArrayList<>();

            ArrayList<ArrayList<HashMap<String, Object>>> nfc_childData = new ArrayList<>();
            ArrayList<HashMap<String, Object>> nfc_east_data = new ArrayList<>();
            ArrayList<HashMap<String, Object>> nfc_north_data = new ArrayList<>();
            ArrayList<HashMap<String, Object>> nfc_south_data = new ArrayList<>();
            ArrayList<HashMap<String, Object>> nfc_west_data = new ArrayList<>();

            for (Team team :
                    teams) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", team.getName());
                map.put("w", "" + team.getWins());
                map.put("l", "" + team.getLosses());
                map.put("t", "" + team.getTies());
                map.put("percent", "" + team.getPercentage());
                if (team.getConference().equals("AFC")) {
                    switch (team.getDivision()) {
                        case "East":
                            map.put("id", "" + (afc_east_data.size() + 1));
                            afc_east_data.add(map);
                            break;
                        case "North":
                            map.put("id", "" + (afc_north_data.size() + 1));
                            afc_north_data.add(map);
                            break;
                        case "South":
                            map.put("id", "" + (afc_south_data.size() + 1));
                            afc_south_data.add(map);
                            break;
                        case "West":
                            map.put("id", "" + (afc_west_data.size() + 1));
                            afc_west_data.add(map);
                            break;
                    }
                } else if (team.getConference().equals("NFC")) {
                    switch (team.getDivision()) {
                        case "East":
                            map.put("id", "" + (nfc_east_data.size() + 1));
                            nfc_east_data.add(map);
                            break;
                        case "North":
                            map.put("id", "" + (nfc_north_data.size() + 1));
                            nfc_north_data.add(map);
                            break;
                        case "South":
                            map.put("id", "" + (nfc_south_data.size() + 1));
                            nfc_south_data.add(map);
                            break;
                        case "West":
                            map.put("id", "" + (nfc_west_data.size() + 1));
                            nfc_west_data.add(map);
                            break;
                    }
                }
            }

            afc_childData.addAll(Arrays.asList(
                    afc_east_data,
                    afc_north_data,
                    afc_south_data,
                    afc_west_data
            ));

            nfc_childData.addAll(Arrays.asList(
                    nfc_east_data,
                    nfc_north_data,
                    nfc_south_data,
                    nfc_west_data
            ));

            ExpandableListView afc_expandableListView = (ExpandableListView) getActivity().findViewById(R.id.afc_exp_list_view);
            afc_expandableListView.setAdapter(new SimpleExpandableListAdapter(
                    getActivity(),
                    afc_groupData,
                    R.layout.standings_group,
                    groupFrom,
                    groupTo,
                    afc_childData,
                    R.layout.standings_list_item,
                    childFrom,
                    childTo
            ));

            ExpandableListView nfc_expandableListView = (ExpandableListView) getActivity().findViewById(R.id.nfc_exp_list_view);
            nfc_expandableListView.setAdapter(new SimpleExpandableListAdapter(
                    getActivity(),
                    nfc_groupData,
                    R.layout.standings_group,
                    groupFrom,
                    groupTo,
                    nfc_childData,
                    R.layout.standings_list_item,
                    childFrom,
                    childTo
            ));

            expandAllGroups(afc_expandableListView);
            expandAllGroups(nfc_expandableListView);
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }

    public void expandAllGroups(ExpandableListView view){
        for (int i = 0; i < view.getExpandableListAdapter().getGroupCount(); i++){
            view.expandGroup(i);
        }

    }


    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    AFC tab1 = new AFC();
                    return tab1;
                case 1:
                    NFC tab2 = new NFC();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
