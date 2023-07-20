package com.retro.logger;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.retro.logger.utils.DraggableFloatingView;

public class FabButton {
    public static void initFab(AppCompatActivity activity){
        if(!(activity instanceof LoggerActivity) && !(activity instanceof LogDetailsActivity) && !(activity instanceof CrashListActivity) && !(activity instanceof CrashDetailActivity) ) {
            ViewGroup root = (ViewGroup) ((ViewGroup) activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);

            if (root.findViewById(R.id.my_looger) == null) {

                DraggableFloatingView fab = new DraggableFloatingView(activity.getApplicationContext());
                fab.setId(R.id.my_looger);
                fab.setLayoutParams(new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                ));
                DraggableFloatingView floater = (DraggableFloatingView) activity.getLayoutInflater().inflate(R.layout.floater_layot, null);
                floater.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.startActivity(new Intent(activity, LoggerActivity.class));
                    }
                });
                fab.addView(floater);
                root.addView(fab);
            }
        }
    }
}