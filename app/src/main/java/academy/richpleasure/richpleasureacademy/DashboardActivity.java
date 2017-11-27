package academy.richpleasure.richpleasureacademy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.speech.RecognizerIntent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import academy.richpleasure.richpleasureacademy.act.ActivitySample;
import academy.richpleasure.richpleasureacademy.infinite.InfiniteFeedInfo;
import academy.richpleasure.richpleasureacademy.infinite.ItemView;
import academy.richpleasure.richpleasureacademy.infinite.LoadMoreView;
import me.relex.circleindicator.CircleIndicator;

public class DashboardActivity extends DrawerActivity {

    @com.mindorks.butterknifelite.annotations.BindView(R.id.loadMoreViewDashboard)
    private InfinitePlaceHolderView mLoadMoreView;

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private Menu collapseMenu;

    private boolean appBarExpanded = true;

    private MaterialSearchView searchView;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.beast,R.drawable.charles,R.drawable.magneto,R.drawable.mystique,R.drawable.wolverine};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dashboard);
        ButterKnifeLite.bind(this);



        init();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        recList = (RecyclerView) findViewById(R.id.scrollableview);

        setSupportActionBar((Toolbar) findViewById(R.id.anim_toolbar));


 /*       setDrawerTheme(
                new DrawerTheme(this)
                        .setBackgroundColorRes(R.color.window_background_3)
                        .setTextColorPrimaryRes(R.color.text_color_primary_3)
                        .setTextColorSecondaryRes(R.color.text_color_secondary_3)
        );
*/
        addItems(new DrawerItem()
                        .setTextPrimary(getString(R.string.profile)),
                new DrawerItem()
                        .setTextPrimary(getString(R.string.inbox)),
                new DrawerItem()
                        .setTextPrimary(getString(R.string.subjects)),
                new DrawerItem()
                        .setTextPrimary(getString(R.string.logout)),
                new DrawerItem()
                        .setTextPrimary(getString(R.string.billing))
        );
        setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                selectItem(position);
                if(position==0)
                {
                    Intent i = new Intent(getApplicationContext(),ActivitySample.class);
                    startActivity(i);
                }else if(position==1)
                {
                    Intent i = new Intent(getApplicationContext(),InboxActivity.class);
                    startActivity(i);
                }else if(position == 2)
                {
                    Intent i = new Intent(getApplicationContext(), SubjectListActivity.class);
                    startActivity(i);
                }else if(position == 3)
                {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
                closeDrawer();
            }
        });

        addProfile(new DrawerProfile()
                .setId(1)
                .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.cat_2))
                .setBackground(ContextCompat.getDrawable(this, R.drawable.cat_wide_1))
                .setName(getString(R.string.lorem_ipsum_short))
        );
        addProfile(new DrawerProfile()
                .setId(2)
                .setRoundedAvatar((BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.cat_1))
                .setBackground(ContextCompat.getDrawable(this, R.drawable.cat_wide_2))
                .setName(getString(R.string.lorem_ipsum_short))
                .setDescription(getString(R.string.lorem_ipsum_medium))
        );

//        collapsingToolbar.setTitle(getString(R.string.featured));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recList.setLayoutManager(llm);

        DessertAdapter adapter = new DessertAdapter();
//        recList.setAdapter(adapter);



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.landscape);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) > 200){
                    appBarExpanded = false;
                }else{
                    appBarExpanded = true;
                }
                invalidateOptionsMenu();
            }
        });



        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 15);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

//        setupView();
        setupSubjectView();
        setSearchView();
    }

    private void setSearchView()
    {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.bringToFront();
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                finish();
                break;
            case R.id.inbox:
                Intent inbox = new Intent(getApplicationContext(),InboxActivity.class);
                startActivity(inbox);
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }

    public void gotoSeminar(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void gotoSubjects(View view) {
        Intent intent = new Intent(this,SubjectsActivity.class);
        startActivity(intent);
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(DashboardActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 200000, 200000);
    }

    public void gotoInbox(View view)
    {
        Intent inbox = new Intent(getApplicationContext(),InboxActivity.class);
        startActivity(inbox);
    }

    private void setupView(){

        List<InfiniteFeedInfo> feedList = Utils.loadInfiniteFeeds(this.getApplicationContext());
        mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, feedList));
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreView.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemView(this.getApplicationContext(), feedList.get(i)));
        }

        // Testing the sorting
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadMoreView.sort(new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 instanceof ItemView && o2 instanceof ItemView) {
                            ItemView view1 = (ItemView) o1;
                            ItemView view2 = (ItemView) o2;
                            return view1.getInfo().getTitle().compareTo(view2.getInfo().getTitle());
                        }
                        return 0;
                    }
                });
            }
        }, 8000);
    }

    private void setupSubjectView(){

        List<SubjectListInfo> subjectList = Utils.loadInfiniteSubjects(this.getApplicationContext());
        mLoadMoreView.setLoadMoreResolver(new LoadMoreSubjectView(mLoadMoreView, subjectList));
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreSubjectView.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new SubjectItemView(this.getApplicationContext(), subjectList.get(i)));
        }

        // Testing the sorting
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadMoreView.sort(new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 instanceof ItemView && o2 instanceof ItemView) {
                            SubjectItemView view1 = (SubjectItemView) o1;
                            SubjectItemView view2 = (SubjectItemView) o2;
                            return view1.getInfo().getSubject_title().compareTo(view2.getInfo().getSubject_title());
                        }
                        return 0;
                    }
                });
            }
        }, 8000);
    }
}
