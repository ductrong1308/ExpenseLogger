package com.example.expenselogger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppMessages;
import com.example.expenselogger.utils.AppUtils;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase wdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wdb = DBoperationSupport.getWritable(this);
        final SharedPreferences sharedPref = SharedPrefHandler.getSharedPref(this);

        TextView signUp = (TextView) findViewById(R.id.textViewSignup);
        signUp.setMovementMethod(LinkMovementMethod.getInstance());

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String email = txtEmail.getText().toString();
                    String password = txtPassword.getText().toString();

                    String selectUserQuery = "SELECT * FROM Users WHERE emailAddress = '" + email + "' AND password = '" + password + "' ";
                    Cursor cursor = wdb.rawQuery(selectUserQuery, null);
                    int size = cursor.getCount();
                    if (size == 0) {
                        AppUtils.ShowErrorMessage(LoginActivity.this, AppMessages.InvalidLoginInfo);
                    } else {
                        cursor.moveToFirst();
                        String userId = cursor.getString(cursor.getColumnIndex("id"));
                        String userFirstName = cursor.getString(cursor.getColumnIndex("firstName"));
                        String userLastName = cursor.getString(cursor.getColumnIndex("lastName"));
                        cursor.close();
                        // Saving these data to SharedPref for later use purpose.
                        SharedPrefHandler.storeData("USERID", userId, LoginActivity.this);
                        SharedPrefHandler.storeData("USERFNAME", userFirstName, LoginActivity.this);
                        SharedPrefHandler.storeData("USERLNAME", userLastName, LoginActivity.this);

                        String userSelectedCurrency = DBoperationSupport.GetUserCurrency(Integer.parseInt(userId));
                        SharedPrefHandler.storeData("CURRENCY", userSelectedCurrency, LoginActivity.this);

                        // Login and clear back stack
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                }
                catch (Exception ex){
                    AppUtils.ShowErrorMessage(LoginActivity.this, ex.getMessage());
                }
            }
        });
    }

    protected void onDestroy() {
        DBoperationSupport.close();
        super.onDestroy();
        Log.d("LoginActivity","onDestroy");
    }
}
