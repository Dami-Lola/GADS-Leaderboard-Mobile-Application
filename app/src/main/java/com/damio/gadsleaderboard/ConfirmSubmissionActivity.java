package com.damio.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.damio.gadsleaderboard.ActivityUtils;
import com.damio.gadsleaderboard.ConfirmSubmissionFragment;
import com.damio.gadsleaderboard.R;

public class ConfirmSubmissionActivity extends AppCompatActivity {

    ConfirmSubmissionFragment confirmSubmissionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fade_in,R.anim.fade_in);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_submission);

        confirmSubmissionFragment = new ConfirmSubmissionFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), confirmSubmissionFragment, R.id.content_frame);
    }
    @Override
    public void onBackPressed() {

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}