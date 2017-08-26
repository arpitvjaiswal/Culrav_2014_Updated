package mnnit.arpitjaiswal.culrav.y14.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import mnnit.arpitjaiswal.culrav.y14.R;
import mnnit.arpitjaiswal.culrav.y14.models.Contact;
import mnnit.arpitjaiswal.culrav.y14.models.MemberContact;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private final ArrayList<MemberContact> team = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        public final TextView tvName, tvDesignation;
        @NonNull
        public final ImageButton ibCall, ibEmail;

        public ViewHolder(@NonNull View itemsView) {
            super(itemsView);

            tvName = (TextView) itemsView.findViewById(R.id.team_member_entry_name);
            tvDesignation = (TextView) itemsView.findViewById(R.id.team_member_entry_designation);
            ibCall = (ImageButton) itemsView.findViewById(R.id.team_member_entry_call);
            ibEmail = (ImageButton) itemsView.findViewById(R.id.team_member_entry_email);
        }
    }

    public TeamAdapter(Context context, @NonNull JSONArray teamMembers) throws JSONException {
        this.context = context;
        int length = teamMembers.length();
        for (int i = 0; i < length; i++) {
            team.add(new MemberContact(teamMembers.getJSONObject(i)));
        }
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_team_member, parent, false);
        return new ViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        MemberContact member = team.get(position);
        final Contact contact = member.getContact();

        holder.tvName.setText(contact.getName());
        holder.tvDesignation.setText(member.getDesignation());

        holder.ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:" + contact.getNumber();
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri
                        .parse(url));
                context.startActivity(Intent.createChooser(callIntent, "Call"));
            }
        });

        holder.ibEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "mailto:" + contact.getEmail();
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri
                        .parse(url));
                context.startActivity(Intent.createChooser(mailIntent,
                        "Open email..."));
            }
        });
    }

    @Override
    public int getItemCount() {
        return team.size();
    }
}
