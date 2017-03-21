package tahainshad.com.app.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tahainshad.com.app.model.Singer;

/**
 * Created by Saeed on 21/03/2017.
 */

public class SingerManager {
    private static final SingerManager instance = new SingerManager();

    private SingerManager() {
    }

    public static SingerManager getInstance() {
        return instance;
    }

    public List<Singer> getAllSingers() {
        String url  = "http://com.tahainshad.myApp.com/welcome/json_singers";
        String json = WebManager.getInstance().callWebMethod(url);

        if (!json.equals("")) {
            return getSingersFromJson(json);
        }

        return new ArrayList<>();
    }

    private ArrayList<Singer> getSingersFromJson(String json) {
        ArrayList<Singer> list = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String country     = jsonObject.getString("country");
                int    id          = jsonObject.getInt("id");
                String singer_name = jsonObject.getString("singer_name");
                String image       = jsonObject.getString("image");
                int    visits      = jsonObject.getInt("visits");
                int    cnt         = jsonObject.getInt("cnt");


                Singer singer = new Singer(country, id, singer_name, image, visits, cnt);
                list.add(singer);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}

