package com.damio.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class SubmitProjectActivity extends AppCompatActivity {


    TextInputEditText firstName, lastName, emailAddress, projectLink;
    Button submitProject;
    String firstNameString, lastNameString, emailAddressString, projectLinkString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailAddress = findViewById(R.id.emailAddress);
        projectLink = findViewById(R.id.projectLink);

        submitProject = findViewById(R.id.submitProjectButton);
        submitProject.setOnClickListener(clickListener);


        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(clickListener);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.backButton:
                    finish();
                    break;
                case R.id.submitProjectButton:

                    firstNameString = firstName.getText().toString().trim();
                    lastNameString = lastName.getText().toString().trim();
                    emailAddressString = emailAddress.getText().toString().trim();
                    projectLinkString = projectLink.getText().toString().trim();

                    if (validate(firstName) && validate(lastName) && validateEmail() && validate(projectLink))
                        pushData();
                    break;
            }

        }
    };

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return true;
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private boolean validateEmail() {
        String email = emailAddressString;
        if (email.isEmpty() || !isValidEmail(email)) {
            emailAddress.setError("Email is not valid.");
            emailAddress.requestFocus();
            return false;
        }
        return true;
    }

    private void pushData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        preferences.edit()
                .putString("firstName", firstNameString)
                .putString("lastName", lastNameString)
                .putString("emailAddress", emailAddressString)
                .putString("projectLink", projectLinkString)
                .apply();
        Intent confirmSubmission = new Intent(getApplicationContext(), ConfirmSubmissionActivity.class);
        startActivity(confirmSubmission);
    }
}