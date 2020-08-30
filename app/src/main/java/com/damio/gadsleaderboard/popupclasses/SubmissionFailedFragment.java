package com.damio.gadsleaderboard.popupclasses;

import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.damio.gadsleaderboard.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SubmissionFailedFragment extends Fragment {

    public void showPopupWindow(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fragment_submission_failed, null);
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0
        );

        if (Build.VERSION.SDK_INT >= 21) {
            popupView.setElevation(5.0f);
        }
    }
}