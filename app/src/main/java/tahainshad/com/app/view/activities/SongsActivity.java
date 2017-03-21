package tahainshad.com.app.view.activities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import tahainshad.com.app.R;
import tahainshad.com.app.control.MyHelper;
import tahainshad.com.app.control.SongsManager;
import tahainshad.com.app.model.Song;
import tahainshad.com.app.view.adapters.SongsListAdapter;

public class SongsActivity extends AppCompatActivity {

    ImageView   singerImg;
    ListView    listSongs;
    MediaPlayer mediaplayer;
    SeekBar     seekBar;
    ImageButton imgBtnPrev;
    ImageButton imgBtnPlay;
    ImageButton imgBtnPause;
    ImageButton imgBtnNext;
    int         progress;
    int         index;
    TextView    currentTime;
    TextView    fullTme;
    List<Song>  dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        // casting views:
        singerImg = (ImageView) findViewById(R.id.imgSinger);
        listSongs = (ListView) findViewById(R.id.songsList);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        currentTime = (TextView) findViewById(R.id.editTextCurrentTime);
        fullTme = (TextView) findViewById(R.id.editTextFullTme);

        // get passed variables جلب المتغيرات القادمة من الأكتيفيتي السابق
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int    singerId       = bundle.getInt("singerId");
            String singerName     = bundle.getString("singerName");
            String singerCountry  = bundle.getString("singerCountry");
            String singerImage    = bundle.getString("singerImage");
            int    singerNumSongs = bundle.getInt("singerNumSongs");
            int    singerVisits   = bundle.getInt("singerVisits");

            mediaplayer = new MediaPlayer();

            /* كائن يحوي قائمة كل الأناشيد للمنشد الحالي */
            dataList = SongsManager.getInstance().getAllSongs(singerId);

            /* تغيير عنوان الـ activity الحالي */
            setTitle(singerName);

            /* جلب الصورة من الانترنت ووضعها كصورة للمنشد */
            String singerPhotoUrl = "http://com.tahainshad.myApp.com/files/images/singers/" + singerImage;
            Picasso.with(SongsActivity.this).load(singerPhotoUrl).into(singerImg);

            /* ملئ الـ listView بالأناشيد */
            fillSongsList(singerId);
        }
        // end get passed variables
    }
    // seekBarMethod end;

    /*  ضبط الـ seekBar من حيث طوله وعند سحبه  */
    private void seekBarMethod() {
        int duration = mediaplayer.getDuration();
        seekBar.setMax(duration);
        fullTme.setText(String.valueOf(MyHelper.getTimeString(duration)));


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaplayer.seekTo(progress);
            }
        });

        /* نيسب يقوم بالتنصت كل ثانية لتغيير مكان السيك بار  */
        myThread thread = new myThread();
        thread.start();

    }

    /* ملئ الـ listView بالأناشيد */
    private void fillSongsList(final int singerId) {
        Thread caller = new Thread(new Runnable() {
            @Override
            public void run() {

                final SongsListAdapter adapter = new SongsListAdapter(SongsActivity.this, R.layout.listitem_song, dataList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listSongs.setAdapter(adapter);

                        /* عند الضغط على نشيد من القائمة */
                        listSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                index = i;
                                playItem(index);
                            }
                        });
                        // end onClick on items
                    }
                });
            }
        });
        caller.start();
    }

    /* ميثود خاص بتشغيل نشيد من القائمة حسب الإندكس تبعو */
    private void playItem(int index) {
        Song   song    = dataList.get(index);
        String songUrl = song.getUrl();
        mediaplayer.stop();
        mediaplayer = new MediaPlayer();
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaplayer.setDataSource(songUrl);
            mediaplayer.prepare();
            mediaplayer.start();
            seekBarMethod();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSong(View view) {
        mediaplayer.start();
    }

    public void pauseSong(View view) {
        mediaplayer.pause();
    }

    public void prevSong(View view) {
        //...
    }

    public void nextSong(View view) {
        //...
        Toast.makeText(SongsActivity.this, String.valueOf(listSongs.getCheckedItemPosition()), Toast.LENGTH_SHORT).show();
    }

    public void stopSong(View view) {
        mediaplayer.stop();
        progress = 0;
        seekBar.setProgress(progress);

        currentTime.setText(R.string.init_time_zeros);
        fullTme.setText(R.string.init_time_zeros);

    }

    /* myThread class: تشغيل كل ثانية لتغيير مكان السيك بار  */
    private class myThread extends Thread {
        public void run() {
            while (mediaplayer != null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seekBar.post(new Runnable() {
                    @Override
                    public void run() {
                        int currentPosition = mediaplayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        currentTime.setText(String.valueOf(MyHelper.getTimeString(currentPosition)));
                        /* عند وصول السيك بار للنهاية */
                        if (seekBar.getProgress() == mediaplayer.getDuration()) {
                            Toast.makeText(SongsActivity.this, "Finish" + String.valueOf(index), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        }
    }
}


