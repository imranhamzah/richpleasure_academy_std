package academy.richpleasure.richpleasureacademy.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import academy.richpleasure.richpleasureacademy.R;


public class SampleHelper implements View.OnClickListener {

    private Activity activity;
    private int theme = R.style.AppThemeDark;

    private SampleHelper(Activity activity) {
        this.activity = activity;
    }

    public static SampleHelper with(Activity activity) {
        return new SampleHelper(activity);
    }

    public SampleHelper init() {
        activity.setTheme(theme);

        return this;
    }

    public void loadAbout() {
        final FrameLayout flHolder = (FrameLayout) activity.findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(activity)
                .setAppName(R.string.app_name)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Imran Hamzah")
                .setMySubjects("My Subjects")
                .setActionsColumnsCount(2)
                .addRemoveAdsAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addShareAction(R.string.app_name)
                .addDonateAction((Intent) null)
                .setDividerDashGap(13)
                .setMyTutors("My Tutors")
                .setLinksColumnsCount(2)
                .addTwitterLink("user")
                .addInstagramLink("jnrvans")
                .addGooglePlusLink("+JuniorVansuita")
                .addYoutubeChannelLink("CaseyNeistat")
                .addDribbbleLink("user")
                .addLinkedInLink("arleu-cezar-vansuita-júnior-83769271")
                .addEmailLink("vansuita.jr@gmail.com")
                .addWhatsappLink("Jr", "+554799650629")
                .addSkypeLink("user")
                .addGoogleLink("user")
                .addAndroidLink("user")
                .addWebsiteLink("site")
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }
}
