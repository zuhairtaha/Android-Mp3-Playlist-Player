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


import com.squareup.picasso.Picasso;

import java.util.List;

import tahainshad.com.app.model.Singer;

/**
 * Created by Saeed on 21/03/2017.
 */

public class SingerListAdapter extends ArrayAdapter<Singer> {
    private List<Singer> singers;
    private Context      context;


    public SingerListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Singer> singers) {
        super(context, resource, singers);
        this.singers = singers;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        @SuppressLint("ViewHolder") View rowView          = inflater.inflate(R.layout.listitem_singer, parent, false);
        Singer                           singer           = singers.get(position);
        TextView                         singerName       = (TextView) rowView.findViewById(R.id.SingerName);
        TextView                         singerStatistics = (TextView) rowView.findViewById(R.id.SingerStatistics);
        ImageView                        singerPhoto      = (ImageView) rowView.findViewById(R.id.SingerPhoto);

        String singerPhotoUrl = "http://com.tahainshad.myApp.com/files/images/singers/50/" + singer.getImage();
        singerName.setText(singer.getSinger_name());

        // http://square.github.io/picasso/
        Picasso.with(context).load(singerPhotoUrl).into(singerPhoto);

        Typeface fa = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");
        singerStatistics.setTypeface(fa);


        singerStatistics.setText("\uf0ac " + singer.getCountry() + "   \uf06e " + singer.getVisits() + "   \uf1c7 " + singer.getCnt());
        return rowView;
    }
}

