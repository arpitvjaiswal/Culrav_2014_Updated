package mnnit.arpitjaiswal.culrav.y14.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mnnit.arpitjaiswal.culrav.y14.R;

public class DevFragment extends Fragment {

    public DevFragment() {
    }

    public static DevFragment newInstance() {
        return new DevFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dev, container, false);
    }
}
