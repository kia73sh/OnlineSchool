package com.sourcey.materiallogindemo.Views.Authentication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sourcey.materiallogindemo.Http.Clients.AuthClient;
import com.sourcey.materiallogindemo.Models.User;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Views.SimpleActivity;
import com.sourcey.materiallogindemo.Views.UI.Components;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends SimpleActivity {

    @Bind(R.id.input_email) EditText emailText;
    @Bind(R.id.input_password) EditText passwordText;
    @Bind(R.id.btn_login) Button loginButton;
    @Bind(R.id.link_signup) TextView signupLink;
    @Bind(R.id.text_input_mail) TextInputLayout textInputMail;
    @Bind(R.id.text_input_password) TextInputLayout textInputPassWord;

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    private void initUI(){
        ButterKnife.bind(this);
        emailText.setTypeface(Components.getIransansFont());
        passwordText.setTypeface(Components.getIransansFont());
        signupLink.setTypeface(Components.getIransansFont());
        loginButton.setTypeface(Components.getIransansFont());

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("اهراز حویت ...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        AuthClient.authorization(user, new AuthClient.OnResponseListener() {
            @Override
            public void onSuccess() {
                onLoginSuccess();
                progressDialog.dismiss();
            }

            @Override
            public void onFailed(int responseCode) {
                onLoginFailed();
                progressDialog.dismiss();
            }
        });
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "خطایی رخ داده", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("ایمیل نامعتبر است .");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("بین ۴ تا ۱۰ حرف");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
