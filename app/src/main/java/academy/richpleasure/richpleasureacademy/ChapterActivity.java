package academy.richpleasure.richpleasureacademy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {

    private RecyclerView chapterRecycleView;
    private ChapterAdapters chapterAdapters;
    private List<Chapters> chaptersList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_item);

        chapterRecycleView = (RecyclerView) findViewById(R.id.chapterRecycleView);
        chaptersList = Utils.loadAvailableChapters(this.getApplicationContext());
        chapterAdapters = new ChapterAdapters(chaptersList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        chapterRecycleView.setLayoutManager(layoutManager);
        chapterRecycleView.setItemAnimator(new DefaultItemAnimator());
        chapterRecycleView.setAdapter(chapterAdapters);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
