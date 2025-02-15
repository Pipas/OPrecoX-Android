package software.pipas.oprecox.modules.customViews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class SquareCardView extends CardView
{
    public SquareCardView(Context context)
    {
        super(context);
    }

    public SquareCardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareCardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
