package academy.richpleasure.richpleasureacademy;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.mindorks.placeholderview.InfinitePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.infinite.LoadMore;

import java.util.List;

@Layout(R.layout.load_more_view)
public class LoadMoreSubjectAvailable {

    public static final int LOAD_VIEW_SET_COUNT = 20;

    private InfinitePlaceHolderView mLoadMoreView;
    private List<Subjects> mSubjectList;

    public LoadMoreSubjectAvailable(InfinitePlaceHolderView loadMoreView, List<Subjects> subjectList) {
        this.mLoadMoreView = loadMoreView;
        this.mSubjectList = subjectList;
    }

    @LoadMore
    private void onLoadMore(){
        Log.d("DEBUG", "onLoadMore");
        new LoadMoreSubjectAvailable.ForcedWaitedLoading();
    }

    class ForcedWaitedLoading implements Runnable{

        public ForcedWaitedLoading() {
            new Thread(this).start();
        }

        @Override
        public void run() {

            try {
                Thread.currentThread().sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int count = mLoadMoreView.getViewCount();
                    Log.d("DEBUG", "count " + count);
                    for (int i = count - 1;
                         i < (count - 1 + LoadMoreSubjectAvailable.LOAD_VIEW_SET_COUNT) && mSubjectList.size() > i;
                         i++) {
                        mLoadMoreView.addView(new SubjectItem(mLoadMoreView.getContext(), mSubjectList.get(i)));

                        if(i == mSubjectList.size() - 1){
                            mLoadMoreView.noMoreToLoad();
                            break;
                        }
                    }
                    mLoadMoreView.loadingDone();
                }
            });
        }
    }
}
