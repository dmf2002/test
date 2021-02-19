package http;

import android.content.Context;
import android.telephony.MbmsGroupCallSession;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.example.hongyan_test1.Event;
import com.example.hongyan_test1.EventAdapter;
import com.example.hongyan_test1.R;
import com.example.hongyan_test1.Show;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SentHttp {

    public void sendPostNetRequest(String mUri, List<Event> eventList, EventAdapter eventAdapter, String Monthday) {
        new Thread(
                () -> {
                    try {
                        URL url = new URL(mUri);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setReadTimeout(8000);
                        connection.setConnectTimeout(8000);
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                        StringBuilder dataToWrite = new StringBuilder();
                        dataToWrite.append("monthday").append("=").append(Monthday);
                        out.writeBytes(dataToWrite.substring(0));
                        connection.connect();
                        InputStream in = connection.getInputStream();
                        String responseData = StreamToString(in);
                        jsonDecodeTest(responseData, eventList, eventAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    private String StreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();
        String oneLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            while ((oneLine = reader.readLine()) != null) {
                sb.append(oneLine).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void jsonDecodeTest(String jsonData, List<Event> eventList, EventAdapter eventAdapter) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            String msg = jsonObject.getString("msg");

            if (msg.equals("success")) {


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String year = jsonObject1.getString("year");
                    String monthday = jsonObject1.getString("monthday");
                    String title = jsonObject1.getString("title");
                    String desc1 = jsonObject1.getString("desc");
                    String type = jsonObject1.getString("type");
                    eventList.add(new Event(year, monthday, title, desc1, type));
                }eventAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
