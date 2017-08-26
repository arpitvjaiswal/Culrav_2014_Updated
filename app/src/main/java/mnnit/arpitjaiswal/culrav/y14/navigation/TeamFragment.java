package mnnit.arpitjaiswal.culrav.y14.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

import mnnit.arpitjaiswal.culrav.y14.R;
import mnnit.arpitjaiswal.culrav.y14.Util;
import mnnit.arpitjaiswal.culrav.y14.adapter.TeamAdapter;

public class TeamFragment extends Fragment {

    public TeamFragment() {
    }

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        setupTeamList((RecyclerView) view.findViewById(R.id.team_list));

        return view;
    }

    private void setupTeamList(RecyclerView recyclerView) {
        JSONArray teamContacts = Util.parseJsonArray(getResources(), R.raw.team_members);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        try {
            recyclerView.setAdapter(new TeamAdapter(getContext(), teamContacts));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setHasFixedSize(true);
    }
}
