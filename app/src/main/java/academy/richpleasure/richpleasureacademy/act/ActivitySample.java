package academy.richpleasure.richpleasureacademy.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import academy.richpleasure.richpleasureacademy.R;
import academy.richpleasure.richpleasureacademy.helper.SampleHelper;


public class ActivitySample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_tutor);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SampleHelper.with(this).init().loadAbout();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
