package software.pipas.oprecox.modules.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.images.ImageManager;

import java.util.ArrayList;

import software.pipas.oprecox.R;
import software.pipas.oprecox.modules.customViews.CustomFontHelper;
import software.pipas.oprecox.modules.dataType.Invite;

public class InviteListAdapter extends ArrayAdapter<Invite>
{
    Context mContext;
    ContentResolver mContentResolver;

    public InviteListAdapter(ArrayList<Invite> data, Context context, ContentResolver contentResolver)
    {
        super(context, R.layout.invite_item_layout, data);
        this.mContext = context;
        this.mContentResolver = contentResolver;
    }

    public void remove(int position)
    {
        super.remove(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View v = convertView;

        if (v == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.invite_item_layout, null);
        }

        Invite invite = getItem(position);

        if (invite != null)
        {
            TextView inviteHostName = (TextView) v.findViewById(R.id.inviteHostName);
            TextView inviteRoomName = (TextView) v.findViewById(R.id.inviteRoomName);
            ImageView inviteImage = (ImageView) v.findViewById(R.id.inviteImage);
            CardView cardViewClickable = (CardView) v.findViewById(R.id.cardViewClickable);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            {
                cardViewClickable.setRadius(0);
            }

            if (inviteHostName != null)
            {
                inviteHostName.setText(invite.getDisplayName());
                CustomFontHelper.setCustomFont(inviteHostName, "font/antipastopro-demibold.otf", mContext);
            }

            if (inviteRoomName != null)
            {
                inviteRoomName.setText(invite.getRealRoomName());
                CustomFontHelper.setCustomFont(inviteRoomName, "font/antipastopro-demibold.otf", mContext);
            }

            if (inviteImage != null)
            {
                ImageManager manager = ImageManager.create(mContext);
                manager.loadImage(inviteImage, invite.getPlayerImage());
            }
        }

        return v;
    }
}
