package com.damio.gadsleaderboard;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.damio.gadsleaderboard.R;
import com.damio.gadsleaderboard.domain.RetrofitInterface;
import com.damio.gadsleaderboard.domain.RetrofitSubmitProjectIntance;
import com.damio.gadsleaderboard.popupclasses.SubmissionFailedFragment;
import com.damio.gadsleaderboard.popupclasses.SubmissionSuccessfulFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class ConfirmSubmissionFragment extends Fragment {

    public ConfirmSubmissionFragment() {
        // Required empty public constructor
    }

    SubmissionSuccessfulFragment submissionSuccessfulFragment;
    SubmissionFailedFragment submissionFailedFragment;

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

                    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Please Wait");
                    progressDialog.show();

                    RetrofitInterface retrofitInterface = RetrofitSubmitProjectIntance.getRetrofitInstance().create(RetrofitInterface.class);
                    Call<SubmitProjectEntity> submitProject = retrofitInterface.submit(restoredEmailAddress, restoredFirstName, restoredLastName, restoredProjectLink);
                    submitProject.enqueue(new Callback<SubmitProjectEntity>() {
                        @Override
                        public void onResponse(Call<SubmitProjectEntity> call, Response<SubmitProjectEntity> response) {

                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                submissionSuccessfulFragment = new SubmissionSuccessfulFragment();
                                submissionSuccessfulFragment.showPopupWindow(getView().getRootView());

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Unable to submit project...Please try later.", Toast.LENGTH_LONG).show();
                                submissionFailedFragment = new SubmissionFailedFragment();
                                submissionFailedFragment.showPopupWindow(getView().getRootView());
                            }
                        }

                        @Override
                        public void onFailure(Call<SubmitProjectEntity> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Something went wrong...Please try later.", Toast.LENGTH_LONG).show();
                            Log.d("Unable to submit post to API.", t.getStackTrace().toString());
                        }
                    });
                    break;
                case R.id.exitButton:
                    getActivity().finish();
                    break;
            }
        }
    };
}