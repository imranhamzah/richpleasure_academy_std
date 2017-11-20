package academy.richpleasure.richpleasureacademy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView full_name = (TextView) findViewById(R.id.full_name);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        final WebView myWebView = (WebView) findViewById(R.id.webview);

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient()); // for alert, anyway.

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://freeschoolmy.docsend.com/view/pkr2rz7");
        myWebView.loadUrl("javascript:var d = document.getElementsByClassName('toolbar-logo_branded-img')[0]; d.setAttribute(\"style\",\"display:none;\");");

        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        Pusher pusher = new Pusher("0761f0d5828f2bed2c8e", options);

        Channel channel = pusher.subscribe("my-channel");

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    myWebView.scrollTo(0, Integer.parseInt(jsonObject.getString("scrollValue")));
                    System.out.println(Integer.parseInt(jsonObject.getString("scrollValue")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        pusher.connect();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        final WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        //noinspection SimplifiableIfStatement
        if (id == R.id.add_math_notes) {
            myWebView.loadUrl("https://freeschoolmy.docsend.com/view/pkr2rz7");
        }else if(id == R.id.matematik_tambahan_notes) {
            myWebView.loadUrl("https://freeschoolmy.docsend.com/view/bkemtyu");
        }else if(id == R.id.matematik_tambahan_ppt) {
            myWebView.loadUrl("https://freeschoolmy.docsend.com/view/in96fiy");
        }else if(id == R.id.physics_notes) {
            myWebView.loadUrl("https://freeschoolmy.docsend.com/view/wyun66d");
        }else if(id == R.id.fizik_ppt) {
            myWebView.loadUrl("https://freeschoolmy.docsend.com/view/25z3mri");
        }else if(id == R.id.math_notes) {
            myWebView.loadUrl("https://freeschoolmy.docsend.com/view/4f4ccur");
        }else if(id == R.id.math_seminar_2016) {
            myWebView.loadUrl("https://drive.google.com/file/d/0B3wpu4AJhV0JeHAzRWF0NWFJLU0/view");
        }else if(id == R.id.math_seminar_answer_2016) {
            myWebView.loadUrl("https://drive.google.com/file/d/0B4UUp6U01PmhY0lKUV80RUp2Mms/view");
        }else if(id == R.id.add_math_seminar_2016) {
            myWebView.loadUrl("https://drive.google.com/file/d/0B3wpu4AJhV0JSEViejBUUkhTNUk/view");
        }else if(id == R.id.physics_seminar_2016) {
            myWebView.loadUrl("https://drive.google.com/file/d/0B3wpu4AJhV0JUWtnd2VnY0I4OHM/view");
        }else if(id == R.id.math_seminar_2015) {
            myWebView.loadUrl("https://drive.google.com/open?id=0B_HUQ8Q1r6QYeTlvX2ZtRUt3LU0");
        }else if(id == R.id.add_math_seminar_2015) {
            myWebView.loadUrl("https://drive.google.com/open?id=0B_HUQ8Q1r6QYSDlLQm9ocFZkRmc");
        }else if(id == R.id.physics_seminar_2015) {
            myWebView.loadUrl("https://drive.google.com/open?id=0B_HUQ8Q1r6QYc3lmSFZaRkppXzA");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class CallAPI extends AsyncTask<String, String, String> {

        public CallAPI(){
            //set context variables if required
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {

            String urlString = params[0]; // URL to call

            String data = params[1]; //data to post

            OutputStream out = null;
            try {

                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                out = new BufferedOutputStream(urlConnection.getOutputStream());

                BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(out, "UTF-8"));

                writer.write(data);

                writer.flush();

                writer.close();

                out.close();

                urlConnection.connect();


            } catch (Exception e) {

                System.out.println(e.getMessage());



            }

            return urlString;
        }
    }

    public void onClickWhatsApp(View view) {

        String smsNumber = "60183954840"; //without '+'
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");

            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi, Tutor Imran...");
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch(Exception e) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
