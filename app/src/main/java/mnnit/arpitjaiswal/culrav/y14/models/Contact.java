package mnnit.arpitjaiswal.culrav.y14.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
    private String name;
    private String email;
    private String number;

    public Contact(JSONObject object) throws JSONException {
        this.name = object.optString("name");
        this.email = object.optString("email");
        this.number = object.optString("number");
    }

    public Contact(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
