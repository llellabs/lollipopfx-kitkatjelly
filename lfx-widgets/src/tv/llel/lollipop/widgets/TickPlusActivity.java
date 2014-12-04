package tv.llel.lollipop.widgets;

import tv.llel.lollipop.widgets.R;
import tv.llel.supportclasses.TickPlusDrawable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;

public class TickPlusActivity extends Activity{

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.tick_plus);

        View view = findViewById(R.id.view);

        final TickPlusDrawable tickPlusDrawable = new TickPlusDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), getResources().getColor(android.R.color.holo_blue_dark), Color.WHITE);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(tickPlusDrawable);
        } else {
            view.setBackground(tickPlusDrawable);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickPlusDrawable.toggle();
            }
        });
    }
    
}
