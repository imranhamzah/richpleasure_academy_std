package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import academy.richpleasure.richpleasureacademy.SubjectListInfo;

/**
 * Created by Imran on 11/26/2017.
 */

@Layout(R.layout.subjects_list)
public class SubjectItemView {

    @View(R.id.subjectTitle)
    private TextView subjectTitle;


    @View(R.id.subjectImage)
    private ImageView subjectImage;


    private SubjectListInfo subjectListInfo;
    private Context subjectContext;
    public SubjectItemView(Context context, SubjectListInfo info)
    {
        subjectListInfo = info;
        subjectContext = context;
    }

    @Resolve
    private void onResolved() {
        subjectTitle.setText(subjectListInfo.getSubject_title());
        Glide.with(subjectContext).load(subjectListInfo.getImageUrl()).into(subjectImage);

    }

    public SubjectListInfo getInfo() {
        return subjectListInfo;
    }
}


