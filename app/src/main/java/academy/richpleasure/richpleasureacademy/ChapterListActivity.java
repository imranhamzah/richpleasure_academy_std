package academy.richpleasure.richpleasureacademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChapterListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChapterAdapter adapter;

    List<Chapters> chaptersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        chaptersList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.chapterRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chaptersList.add(
                new Chapters(
                        "Chapter 1","30","30","40"
                ));

        chaptersList.add(
                new Chapters(
                        "Chapter 2","30","30","40"
                ));

        chaptersList.add(
                new Chapters(
                        "Chapter 3","30","30","40"
                ));

        adapter = new ChapterAdapter(this,chaptersList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
