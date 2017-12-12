package academy.richpleasure.richpleasureacademy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


@Layout(R.layout.subjects_list)
public class SubjectItem {

    @View(R.id.subjectName)
    private TextView subjectName;


    @View(R.id.subjectIcon)
    private ImageView subjectIcon;


    private Subjects subjects;
    private Context subjectContext;
    public SubjectItem(Context context, Subjects info)
    {
        subjects = info;
        subjectContext = context;
    }

    @Click(R.id.subjectFrame)
    public void onClick()
    {
        Intent gotoChapter = new Intent(subjectContext.getApplicationContext(),ChapterActivity.class);
        gotoChapter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        subjectContext.startActivity(gotoChapter);
    }


    @Resolve
    private void onResolved() {
        subjectName.setText(subjects.getSubjectNameStd());

//        Glide.with(subjectContext).load(subjects.getIconFilename()).into(subjectIcon);
    }

    public Subjects getInfo() {
        return subjects;
    }



}


