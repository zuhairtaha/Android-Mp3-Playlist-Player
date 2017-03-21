package tahainshad.com.app.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import tahainshad.com.app.model.Song;

/**
 * Created by Saeed on 21/03/2017.
 */

public class SongsListAdapter extends ArrayAdapter<Song> {
    private List<Song> songs;
    private Context    context;


    public SongsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Song> songs) {
        super(context, resource, songs);
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.listitem_song, parent, false);
        Song                             song    = songs.get(position);


        ImageView songIcon = (ImageView) rowView.findViewById(R.id.SongIcon);
        TextView  songName = (TextView) rowView.findViewById(R.id.SongName);
        TextView  songDesc = (TextView) rowView.findViewById(R.id.SongDesc);

        songName.setText(song.getName());

        /* إدراج خط fontawesome */
        Typeface fa = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");
        songDesc.setTypeface(fa);
        String songDescription = "\uf06e " + String.valueOf(song.getVisits());
        songDesc.setText(songDescription);

        /* وضع أيقونة إيقاع أو بدون */
        if (song.getMusic() == 1)
            songIcon.setImageResource(R.drawable.music);
        else
            songIcon.setImageResource(R.drawable.microphone);

        return rowView;
    }
}
