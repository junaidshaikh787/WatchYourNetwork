package com.retro.logger;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.retro.logger.utils.DraggableFloatingView;

public class FabButton {
    public static void initFab(AppCompatActivity activity) {

        if (!(activity instanceof LoggerActivity) &&
                !(activity instanceof LogDetailsActivity) &&
                !(activity instanceof CrashListActivity) &&
                !(activity instanceof CrashDetailActivity) &&
                !(activity instanceof LogSharedPrefActivity) &&
                !(activity instanceof SPManagerActivity)) {
            ViewGroup root = (ViewGroup) ((ViewGroup) activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);

            if (root.findViewById(R.id.my_looger) == null) {

                FrameLayout fab = new FrameLayout(activity.getApplicationContext());
                fab.setId(R.id.my_looger);
                if (!(root instanceof ConstraintLayout)) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(10, 250, 0, 0);
                    fab.setLayoutParams(layoutParams);
                }else{

                }
                fab.setElevation(100);
                DraggableFloatingView floater = (DraggableFloatingView) activity.getLayoutInflater().inflate(R.layout.floater_layot, null);
                floater.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.startActivity(new Intent(activity, LoggerActivity.class));
                    }
                });
                fab.addView(floater);
                if (root.getChildAt(0) instanceof ViewGroup) {
                    ((ViewGroup) root.getChildAt(0)).addView(fab);
                } else {
                    root.addView(fab);
                }

//                if(root instanceof ConstraintLayout) {
//                    ConstraintLayout constraintLayout=(ConstraintLayout) root;
//                    ConstraintSet constraintSet = new ConstraintSet();
//                    constraintSet.clone(constraintLayout);
//
//                    constraintSet.connect(fab.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
//                    constraintSet.connect(fab.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 0);
//                    constraintSet.constrainDefaultHeight(fab.getId(), 200);
//                    constraintSet.applyTo((ConstraintLayout) root);
//                }
            }
        }
    }
}