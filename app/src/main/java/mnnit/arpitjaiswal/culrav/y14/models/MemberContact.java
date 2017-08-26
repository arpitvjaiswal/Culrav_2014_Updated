package mnnit.arpitjaiswal.culrav.y14.models;

import org.json.JSONException;
import org.json.JSONObject;

public class MemberContact {

    private Contact contact;
    private String designation;

    public MemberContact(JSONObject object) throws JSONException {
        this.contact = new Contact(object);
        this.designation = object.optString("designation");
    }

    public Contact getContact() {
        return contact;
    }

    public String getDesignation() {
        return designation;
    }
}
