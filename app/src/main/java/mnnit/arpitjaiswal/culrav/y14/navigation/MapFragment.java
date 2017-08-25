package mnnit.arpitjaiswal.culrav.y14.navigation;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import mnnit.arpitjaiswal.culrav.y14.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    public MapFragment() {
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mapView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mnnit_map);
        mapFragment.getMapAsync(this);

        return mapView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.map_style));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        try {
            GeoJsonLayer layer = new GeoJsonLayer(googleMap, R.raw.map_markers, getActivity());
            layer.addLayerToMap();
            for (GeoJsonFeature feature : layer.getFeatures()) {
                GeoJsonPointStyle style = new GeoJsonPointStyle();
                style.setTitle(feature.getProperty("title"));
                feature.setPointStyle(style);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
