package com.sourcey.materiallogindemo.Views.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sourcey.materiallogindemo.Config.Paths;
import com.sourcey.materiallogindemo.Models.LoginModel;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Views.Components;
import com.sourcey.materiallogindemo.Views.SimpleActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends SimpleActivity {

    @Bind(R.id.input_email) EditText emailText;
    @Bind(R.id.input_password) EditText passwordText;
    @Bind(R.id.btn_login) Button loginButton;
    @Bind(R.id.link_signup) TextView signupLink;
    @Bind(R.id.text_input_mail) TextInputLayout textInputMail;
    @Bind(R.id.text_input_password) TextInputLayout textInputPassWord;
    Paths url ;
    RequestQueue queue;
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue = Volley.newRequestQueue(LoginActivity.this);
        url = new Paths();
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
        progressDialog.setMessage("لطفا کمی صبر کنید...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        LoginModel user = new LoginModel();
        user.setEmail(email);
        user.setPassWord(password);
        LoginReq(user);


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

    public void LoginReq(LoginModel Login){
        JSONObject bodyParams = new JSONObject();
        try {

            // here should send Login Data
            bodyParams.put("Email",Login.getEmail());
            bodyParams.put("PassWord",Login.getPassWord());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url.LOGIN_URL,bodyParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // here get Response

                    //response.getString("Message"));


                // after saving response
                onLoginSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                int type=0;
                if (error instanceof TimeoutError) {
                    type=1;

                }
                else if(error instanceof NoConnectionError){
                    type=2;

                }
                else if (error instanceof ServerError) {
                    type=3;

                    //TODO
                } else if (error instanceof NetworkError) {
                    type=4;

                }
                onLoginFailed();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new ArrayMap<String, String>();

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(35000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }
}
