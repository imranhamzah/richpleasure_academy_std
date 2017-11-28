package academy.richpleasure.richpleasureacademy.infinite;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import academy.richpleasure.richpleasureacademy.R;

/**
 * Created by janisharali on 24/08/16.
 */
@Layout(R.layout.load_more_item_view)
public class ItemViewTutors {

    @View(R.id.titleTxt)
    private TextView titleTxt;

    @View(R.id.captionTxt)
    private TextView captionTxt;

    @View(R.id.timeTxt)
    private TextView timeTxt;

    @View(R.id.imageView)
    private ImageView imageView;

    private InfiniteTutorInfo mInfo;
    private Context mContext;

    public ItemViewTutors(Context context, InfiniteTutorInfo info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    private void onResolved() {
        titleTxt.setText(mInfo.getTutorName());
        captionTxt.setText(mInfo.getTutorSubjects());
    }

    public InfiniteTutorInfo getInfo() {
        return mInfo;
    }
}
