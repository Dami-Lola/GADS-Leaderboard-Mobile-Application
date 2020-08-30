package com.damio.gadsleaderboard.popupclasses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damio.gadsleaderboard.R;

public class SubmissionFailedFragment extends Fragment {

    public SubmissionFailedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_submission_failed, container, false);
        return root;
    }
}