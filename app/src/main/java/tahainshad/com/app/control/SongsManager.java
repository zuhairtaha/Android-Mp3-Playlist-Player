package tahainshad.com.app.control;

import java.util.List;

import tahainshad.com.app.model.Song;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Saeed on 21/03/2017.
 */

public class SongsManager {
    private static final SongsManager instance = new SongsManager();

    private SongsManager() {
    }

    public static SongsManager getInstance() {
        return instance;
    }

    public List<Song> getAllSongs(int singer_id) {
        String url  = "http://com.tahainshad.myApp.com/welcome/json_songs/" + singer_id;
        String json = WebManager.getInstance().callWebMethod(url);

        if (!json.equals("")) {
            return getSongsFromJson(json);
        }

        return new ArrayList<>();
    }

    private ArrayList<Song> getSongsFromJson(String json) {
        ArrayList<Song> list = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int    id        = jsonObject.getInt("id");
                int    singer_id = jsonObject.getInt("singer_id");
                String name      = jsonObject.getString("name");
                String url       = jsonObject.getString("url");
                String lyrics    = jsonObject.getString("lyrics");
                int    music     = jsonObject.getInt("music");
                int    visits    = jsonObject.getInt("visits");

                Song song = new Song(id, singer_id, name, url, lyrics, music, visits);


                list.add(song);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
