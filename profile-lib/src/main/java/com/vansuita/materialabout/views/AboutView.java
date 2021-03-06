package com.vansuita.materialabout.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vansuita.materialabout.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.builder.Item;
import com.vansuita.materialabout.util.RippleUtil;
import com.vansuita.materialabout.util.VisibleUtil;




public final class AboutView extends FrameLayout {

    private LayoutInflater layoutInflater;

    private CardView cvHolder;
    private CircleImageView ivPhoto;
    private ImageView ivCover;
    private TextView tvName;
    private TextView tvMySubjects;
    private TextView tvMyTutors;

    private AutoFitGridLayout vActions;
    private AutoFitGridLayout vMyTutors;

    private Boolean isDarker;
    private int iconColor = 0;
    private int animationDelay = 200;


    public AboutView(Context context) {
        this(context, null);
    }

    public AboutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AboutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(AboutBuilder bundle) {
        layoutInflater = LayoutInflater.from(getContext());

        ViewGroup holder = this;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (bundle.isWrapScrollView()) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ScrollView scrollView = new ScrollView(getContext());
            scrollView.setLayoutParams(lp);
            addView(scrollView);
            holder = new FrameLayout(getContext());
            holder.setLayoutParams(lp);
            scrollView.addView(holder);
        }

        setLayoutParams(lp);

        layoutInflater.inflate(R.layout.xab_about_layout_card, holder);
    }

    private void bind() {
        cvHolder = (CardView) findViewById(R.id.card_holder);
        ivPhoto = (CircleImageView) findViewById(R.id.photo);
        ivCover = (ImageView) findViewById(R.id.cover);
        tvName = (TextView) findViewById(R.id.name);
        tvMySubjects = (TextView) findViewById(R.id.my_subjects);
        tvMyTutors = (TextView) findViewById(R.id.my_tutors);

        vActions = (AutoFitGridLayout) findViewById(R.id.actions);
        vMyTutors = (AutoFitGridLayout) findViewById(R.id.my_tutor_list);
    }

    public void build(AboutBuilder bundle) {
        init(bundle);
        bind();

        setupCard(bundle);

        tvName.setText(bundle.getName());
        VisibleUtil.handle(tvName, bundle.getName());

        tvMySubjects.setText(bundle.getMySubjects());
        VisibleUtil.handle(tvMySubjects, bundle.getMySubjects());

        tvMyTutors.setText(bundle.getMyTutors());
        VisibleUtil.handle(tvMyTutors, bundle.getMyTutors());

        setBitmap(ivCover, bundle.getCover());
        setBitmap(ivPhoto, bundle.getPhoto());

        setTextColor(tvName, bundle.getNameColor());
        setTextColor(tvMySubjects, bundle.getSubTitleColor());

        this.iconColor = bundle.getIconColor();

        if (bundle.getBackgroundColor() != 0)
            cvHolder.setCardBackgroundColor(bundle.getBackgroundColor());

        if (bundle.getActionsColumnsCount() != 0)
            vActions.setColumnCount(bundle.getActionsColumnsCount());

        if (bundle.getTutorsColumnsCount() != 0)
            vMyTutors.setColumnCount(bundle.getTutorsColumnsCount());


        vMyTutors.setColumnCount(bundle.getTutors().isEmpty() ? GONE : VISIBLE);
        vActions.setVisibility(bundle.getActions().isEmpty() ? GONE : VISIBLE);

        loadActions(bundle);
        loadMyTutors(bundle);
    }


    private void setTextColor(TextView tv, int color) {
        if (color != 0)
            tv.setTextColor(color);
    }

    @SuppressWarnings("ResourceAsColor")
    private void setDivider(AboutBuilder bundle, View holder) {
        if (bundle.isShowDivider()) {

            int color = bundle.getDividerColor();

            if (color == 0)
                color = isDarker() ? Color.GRAY : getNameColor();

            GradientDrawable drawable = ((GradientDrawable) ((LayerDrawable) holder.getBackground()).findDrawableByLayerId(R.id.stroke));

            if (drawable != null) {
                drawable.setStroke(bundle.getDividerHeight(), color, bundle.getDividerDashWidth(), bundle.getDividerDashGap());
            }
        } else {
            RippleUtil.background(holder, (Drawable) null);
        }
    }

    private int getNameColor() {
        return tvName.getCurrentTextColor();
    }

    private boolean isDarker() {
        if (isDarker == null)
            isDarker = RippleUtil.isDark(getCardColor());

        return isDarker;
    }

    private int getCardColor() {
        return cvHolder.getCardBackgroundColor().getDefaultColor();
    }

    private int getIconColor() {
        if (iconColor == 0)
            iconColor = isDarker() ? Color.WHITE : getNameColor();

        return iconColor;
    }

    private void setBitmap(ImageView iv, Bitmap bitmap) {
        if (bitmap == null) {
            iv.setVisibility(GONE);
        } else {
            iv.setImageBitmap(bitmap);
        }
    }

    private void animate(final View v) {
        v.setVisibility(INVISIBLE);

        animationDelay += 20;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setVisibility(VISIBLE);
                v.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.expand_in));
            }
        }, animationDelay);
    }

    private void loadActions(AboutBuilder bundle) {
        for (Item item : bundle.getActions()) {
            addItem(vActions, R.layout.xab_each_action, item);
        }
    }

    private void loadMyTutors(AboutBuilder bundle) {
        for (Item item : bundle.getLinks()) {
            addItem(vMyTutors, R.layout.xab_each_link, item);
        }
    }

    private View addItem(ViewGroup holder, int layout, Item item) {
        View view = layoutInflater.inflate(layout, null);
        view.setId(item.getId());

        TextView tvLabel = (TextView) view.findViewById(R.id.label);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.icon);

//        Icon.on(ivIcon).bitmap(item.getIcon()).color(getIconColor()).put();

        tvLabel.setText(item.getLabel());
        view.setOnClickListener(item.getOnClick());

        RippleUtil.backgroundRipple(view, getCardColor());

        holder.addView(view);
        return view;
    }

    private void setupCard(AboutBuilder bundle) {
        if (!bundle.isShowAsCard()) {
            cvHolder.setCardElevation(0);
            cvHolder.setRadius(0);
            cvHolder.setUseCompatPadding(false);
            cvHolder.setMaxCardElevation(0);
            cvHolder.setPreventCornerOverlap(false);
        }
    }

    public CardView getHolder() {
        return cvHolder;
    }

    public View findItem(int id) {
        return cvHolder.findViewById(id);
    }

    public View findItem(Item item) {
        return findItem(item.getId());
    }

}
