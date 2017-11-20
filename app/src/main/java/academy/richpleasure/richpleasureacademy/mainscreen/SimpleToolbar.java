package academy.richpleasure.richpleasureacademy.mainscreen;

import android.content.Context;
import android.util.AttributeSet;

import academy.richpleasure.richpleasureacademy.R;
import academy.richpleasure.richpleasureacademy.TransformingToolbar;

/**
 * A white Toolbar with a Search icon as Up
 */
public class SimpleToolbar extends TransformingToolbar {

    public SimpleToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(android.R.color.white));
        setNavigationIcon(R.drawable.ic_action_search);
    }

}
