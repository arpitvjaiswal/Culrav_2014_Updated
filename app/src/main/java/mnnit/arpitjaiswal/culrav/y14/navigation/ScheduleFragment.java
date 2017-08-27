package mnnit.arpitjaiswal.culrav.y14.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mnnit.arpitjaiswal.culrav.y14.R;
import mnnit.arpitjaiswal.culrav.y14.adapter.ScheduleAdapter;

public class ScheduleFragment extends Fragment {

    public ScheduleFragment() {
    }

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        setupScheduleList((RecyclerView) view.findViewById(R.id.schedule_list));

        return view;
    }

    private void setupScheduleList(RecyclerView recyclerView) {
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new ScheduleAdapter());
        recyclerView.setHasFixedSize(true);
    }


}
