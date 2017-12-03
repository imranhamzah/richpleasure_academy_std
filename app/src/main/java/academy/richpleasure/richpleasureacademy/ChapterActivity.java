package academy.richpleasure.richpleasureacademy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_item);

        ArrayList<String> list = new ArrayList<String>();
        list.add("item 1");
        list.add("item 2");

        ChapterAdapters adapter = new ChapterAdapters(list,this);

        ListView listView = (ListView) findViewById(R.id.chapter_list_view);
        listView.setAdapter(adapter);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
