package com.damio.gadsleaderboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.damio.gadsleaderboard.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class ConfirmSubmissionFragment extends Fragment {

    public ConfirmSubmissionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_confirm_submit, container, false);
        TextView yesAnswer = root.findViewById(R.id.yesAnswer);
        yesAnswer.setOnClickListener(selectedClicked);
        ImageView existButton = root.findViewById(R.id.exitButton);
        existButton.setOnClickListener(selectedClicked);


        return root;
    }

    View.OnClickListener selectedClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.yesAnswer:
                    String restoredFirstName, restoredLastName, restoredEmailAddress, restoredProjectLink;
                    SharedPreferences prefs = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    restoredFirstName = prefs.getString("firstName", null);
                    restoredLastName = prefs.getString("lastName", null);
                    restoredEmailAddress = prefs.getString("emailAddress", null);
                    restoredProjectLink = prefs.getString("projectLink", null);


                    Log.i(TAG, "FIRSTNAME: " + restoredFirstName);
                    Log.i(TAG, "LASTNAME: " + restoredLastName);
                    Log.i(TAG, "EMAILADDRESS: " + restoredEmailAddress);
                    Log.i(TAG, "PROJECTLINK: " + restoredProjectLink);
                    break;
                case R.id.exitButton:
                    getActivity().finish();
                    break;
            }
        }
    };
}