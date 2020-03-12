package com.example.expenselogger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expenselogger.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText txtLastName = (EditText) findViewById(R.id.txtLastName);
        final EditText txtSignUpEmail = (EditText) findViewById(R.id.txtSignUpEmail);
        final EditText txtSignUpPassword = (EditText) findViewById(R.id.txtSignUpPassword);
        final EditText txtSignUpRePassword = (EditText) findViewById(R.id.txtSignUpRePassword);
        Button btnSignUpSignIn = (Button) findViewById(R.id.btnSignUpSignIn);

        btnSignUpSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errorMessage = "";
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String email = txtSignUpEmail.getText().toString();
                String password = txtSignUpPassword.getText().toString();
                String rePassword = txtSignUpRePassword.getText().toString();

                if(firstName.length() == 0){
                    errorMessage = "Please enter your First Name";
                    txtFirstName.requestFocus();
                }
                else if(lastName.length() == 0){
                    errorMessage = "Please enter your Last Name";
                    txtLastName.requestFocus();
                }
                else if(email.length() == 0 || !isValidEmail(email)){
                    errorMessage = "Invalid email address";
                    txtSignUpEmail.requestFocus();
                }

                else if (password.length() == 0){
                    errorMessage = "Please enter a password";
                    txtSignUpPassword.requestFocus();
                }

                else if (rePassword.length() == 0){
                    errorMessage = "Please re-enter your password";
                    txtSignUpRePassword.requestFocus();
                }

                boolean isPasswordMatches = password.length() != 0 && rePassword.length() != 0
                        && password.length() == rePassword.length() && password.equals(rePassword);
                if(!isPasswordMatches){
                    errorMessage = "Password and confirm password does not match";
                }

                if(errorMessage.length() == 0){

                    // Storing data to DB

                    // Then start main activity
                    finish();
                    Intent main = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(main);
                }
                else {
                    Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
