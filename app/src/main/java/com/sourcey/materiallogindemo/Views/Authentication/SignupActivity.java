package com.sourcey.materiallogindemo.Views.Authentication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
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
import com.sourcey.materiallogindemo.Models.User;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.Views.Components;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    Paths url ;
    RequestQueue queue;
    private static final String TAG = "SignupActivity";

    @Bind(R.id.input_name) EditText nameText;
    @Bind(R.id.input_email) EditText emailText;
    @Bind(R.id.input_password) EditText passwordText;
    @Bind(R.id.btn_signup) Button signupButton;
    @Bind(R.id.link_login) TextView loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        queue = Volley.newRequestQueue(SignupActivity.this);
        url = new Paths();
        initUI();
    }

    private void initUI(){
        ButterKnife.bind(this);
        nameText.setTypeface(Components.getIransansFont());
        emailText.setTypeface(Components.getIransansFont());
        passwordText.setTypeface(Components.getIransansFont());
        signupButton.setTypeface(Components.getIransansFont());
        loginLink.setTypeface(Components.getIransansFont());

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("در حال ثبت نام ...");
        progressDialog.show();

        User user = new User();
        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        // user sign up data model
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);



    }

    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "خطایی رخ داده", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    // here add sign up method
    public void LoginReq(User SignUpUser){
        JSONObject bodyParams = new JSONObject();
        try {

            // here should send SignUp Data
            bodyParams.put("Email",SignUpUser.getEmail());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url.SIGNUP_URL,bodyParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // here get Response

                //response.getString("Message"));


                // after saving response
                onSignupSuccess();
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
                onSignupFailed();

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

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("حداقل ۳ کارکتر");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("ایمیل نا معتبر است");
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