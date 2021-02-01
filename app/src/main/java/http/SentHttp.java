package http;

import androidx.viewpager.widget.PagerAdapter;

import com.example.hongyan_test1.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SentHttp {
    public void sendPostNetRequest(String mUri, String monthday, List<Event> eventList) {
        new Thread(
                ()->{
                    try{
                        URL url=new URL(mUri);
                        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                        connection.setRequestMethod("post");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.connect();
                        OutputStream outputStream=connection.getOutputStream();
                        outputStream.write(monthday.getBytes());
                        InputStream in=connection.getInputStream();
                        String responseData=StreamToString(in);
                        jsonDecodeTest(responseData,eventList);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
    private String StreamToString(InputStream in) {
        StringBuilder sb=new StringBuilder();
        String oneLine;
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try {
            while ((oneLine=reader.readLine())!=null) {
                sb.append(oneLine).append('\n');
            }
        }
        catch (Exception e)  {
            e.printStackTrace();
         }
        finally {
            try{
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    private void jsonDecodeTest(String jsonData,List<Event> eventList) {
        try {
            JSONArray jsonArray=new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String years=jsonObject.getString("years");
                String monthday=jsonObject.getString("monthday");
                String title =jsonObject.getString("title");
                String desc1=jsonObject.getString("desc");
                String type=jsonObject.getString("type");
                eventList.add(new Event(years,monthday,title,desc1,type));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
