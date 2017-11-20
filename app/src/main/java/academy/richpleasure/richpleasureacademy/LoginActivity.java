package academy.richpleasure.richpleasureacademy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.List;
import butterknife.ButterKnife;
import butterknife.BindViews;
import android.support.annotation.ColorRes;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @BindViews(value = {R.id.logo,R.id.second,R.id.last})
    protected List<ImageView> sharedElements;

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent gotoDashboard = new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(gotoDashboard);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login attempt canceled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Login attempt failed.", Toast.LENGTH_SHORT).show();
            }
        });


        ButterKnife.bind(this);
        final AnimatedViewPager pager= ButterKnife.findById(this,R.id.pager);
        final ImageView background=ButterKnife.findById(this,R.id.scrolling_background);
        int[] screenSize=screenSize();

        for(ImageView element:sharedElements){
            @ColorRes int color=element.getId()!=R.id.logo?R.color.white_transparent:R.color.color_logo_log_in;
            DrawableCompat.setTint(element.getDrawable(), ContextCompat.getColor(this,color));
        }
        //load a very big image and resize it, so it fits our needs
        Glide.with(this)
                .load(R.drawable.busy)
                .asBitmap()
                .override(screenSize[0]*2,screenSize[1])
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new ImageViewTarget<Bitmap>(background) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        background.setImageBitmap(resource);
                        background.post(()->{
                            //we need to scroll to the very left edge of the image
                            //fire the scale animation
                            background.scrollTo(-background.getWidth()/2,0);
                            ObjectAnimator xAnimator=ObjectAnimator.ofFloat(background,View.SCALE_X,4f,background.getScaleX());
                            ObjectAnimator yAnimator=ObjectAnimator.ofFloat(background,View.SCALE_Y,4f,background.getScaleY());
                            AnimatorSet set=new AnimatorSet();
                            set.playTogether(xAnimator,yAnimator);
                            set.setDuration(getResources().getInteger(R.integer.duration));
                            set.start();
                        });
                        pager.post(()->{
                            AuthAdapter adapter = new AuthAdapter(getSupportFragmentManager(), pager, background, sharedElements);
                            pager.setAdapter(adapter);
                        });
                    }
                });





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    private int[] screenSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new int[]{size.x,size.y};
    }

    public void gotoDashboard(View view)
    {
        Intent gotoDashboard = new Intent(getApplicationContext(),DashboardActivity.class);
        startActivity(gotoDashboard);
    }
}
