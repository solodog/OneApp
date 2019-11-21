package util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17524 on 2019/10/28.
 */

public class Httphelp {
    StringBuffer response;
    List<data> datasResult=new ArrayList<>();
    public List<data> getdata(String name) {
        try {
            URL url = new URL("https://api.apiopen.top/searchMusic?name="+name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            response = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return  getjson(response.toString());
    }
    List<data> getjson(String data){
        Gson gson=new Gson();
        Type jsonType = new TypeToken<datas>() {}.getType();
        datas datas = new Gson().fromJson(data, jsonType);

        for(int i=0;i<datas.getResult().size();i++) {
            util.data data1=new data();
            data1.url = datas.getResult().get(i).getUrl();
            data1.title = datas.getResult().get(i).getTitle();
            data1.author = datas.getResult().get(i).getAuthor();
            data1.pic = datas.getResult().get(i).getPic();
            data1.lrc= datas.getResult().get(i).getLrc();
            datasResult.add(data1);
        }
        return datasResult;
    }
}
