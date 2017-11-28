package academy.richpleasure.richpleasureacademy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.Comparator;
import java.util.List;

import academy.richpleasure.richpleasureacademy.infinite.InfiniteFeedInfo;
import academy.richpleasure.richpleasureacademy.infinite.InfiniteTutorInfo;
import academy.richpleasure.richpleasureacademy.infinite.ItemView;
import academy.richpleasure.richpleasureacademy.infinite.ItemViewTutors;
import academy.richpleasure.richpleasureacademy.infinite.LoadMoreView;
import academy.richpleasure.richpleasureacademy.infinite.LoadMoreViewTutors;

public class TutorListActivity extends AppCompatActivity {

    @com.mindorks.butterknifelite.annotations.BindView(R.id.loadMoreViewTutors)
    private InfinitePlaceHolderView mLoadMoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setupView();
    }

    private void setupView(){

        List<InfiniteTutorInfo> feedTutorList = Utils.loadInfiniteTutorFeeds(this.getApplicationContext());
        mLoadMoreView.setLoadMoreResolver(new LoadMoreViewTutors(mLoadMoreView, feedTutorList));
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreViewTutors.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreViewTutors.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemViewTutors(this.getApplicationContext(), feedTutorList.get(i)));
        }

        // Testing the sorting
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadMoreView.sort(new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 instanceof ItemView && o2 instanceof ItemView) {
                            ItemViewTutors view1 = (ItemViewTutors) o1;
                            ItemViewTutors view2 = (ItemViewTutors) o2;
                            return view1.getInfo().getTutorName().compareTo(view2.getInfo().getTutorName());
                        }
                        return 0;
                    }
                });
            }
        }, 8000);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
