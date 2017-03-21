package tahainshad.com.app.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import tahainshad.com.app.R;
import tahainshad.com.app.control.SingerManager;
import tahainshad.com.app.model.Singer;
import tahainshad.com.app.view.adapters.SingerListAdapter;

public class SingersActivity extends AppCompatActivity {
    ListView listSingers;
    @Override

    // start onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singers);

        listSingers = (ListView) findViewById(R.id.listSingers);
        fillSingersList();
    } // end onCreate;


    private void fillSingersList() {
        Thread caller = new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Singer>      dataList = SingerManager.getInstance().getAllSingers();
                final SingerListAdapter adapter  = new SingerListAdapter(SingersActivity.this, R.layout.listitem_singer, dataList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listSingers.setAdapter(adapter);

                        // onClick on items:
                        listSingers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Singer singer = dataList.get(i);

                                int    singerId       = singer.getId();
                                String singerName     = singer.getSinger_name();
                                String singerCountry  = singer.getCountry();
                                String singerImage    = singer.getImage();
                                int    singerNumSongs = singer.getCnt();
                                int    singerVisits   = singer.getVisits();

                                Intent intent = new Intent(SingersActivity.this, SongsActivity.class);
                                intent.putExtra("singerId", singerId);
                                intent.putExtra("singerName", singerName);
                                intent.putExtra("singerCountry", singerCountry);
                                intent.putExtra("singerImage", singerImage);
                                intent.putExtra("singerNumSongs", singerNumSongs);
                                intent.putExtra("singerVisits", singerVisits);
                                startActivity(intent);
                            }
                        });

                        // end onClick on items
                    }
                });
            }
        });
        caller.start();
    }
}
