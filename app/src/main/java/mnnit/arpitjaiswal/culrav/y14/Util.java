package mnnit.arpitjaiswal.culrav.y14;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class Util {

    private Util() {
        throw new AssertionError();
    }

    public static JSONObject parseJsonObject(Resources resources, int resId) {
        JSONObject object = null;
        try {
            InputStream inputStream = resources.openRawResource(resId);

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            object = new JSONObject(new String(buffer, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    public static JSONArray parseJsonArray(Resources resources, int resId) {
        JSONArray array = null;
        try {
            InputStream inputStream = resources.openRawResource(resId);

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            array = new JSONArray(new String(buffer, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }
}
