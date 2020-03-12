package com.example.expenselogger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.db.DBoperationSupport;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase wdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wdb = DBoperationSupport.getWritable(this);

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
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                String selectUserQuery = "SELECT * FROM Users WHERE emailAddress = '" + email + "' AND password = '" + password + "' ";
                Cursor cursor = wdb.rawQuery(selectUserQuery, null);
                int size = cursor.getCount();
                if (size == 0) {
                    Toast.makeText(LoginActivity.this, "Invalid email and/or password", Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    String userId = cursor.getString(cursor.getColumnIndex("id"));
                    String userFirstName = cursor.getString(cursor.getColumnIndex("firstName"));
                    String userLastName = cursor.getString(cursor.getColumnIndex("lastName"));
                    SharedPrefHandler.storeData("USERID", userId, LoginActivity.this);
                    SharedPrefHandler.storeData("USERFNAME", userId, LoginActivity.this);
                    SharedPrefHandler.storeData("USERLNAME", userId, LoginActivity.this);

                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main);
                }
            }
        });
    }
}