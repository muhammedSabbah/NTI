package com.example.nti.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nti.R;
import com.example.nti.pojo.SessionManager;
import com.example.nti.ui.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;

    private String email;
    private String password;

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private ProgressDialog progressDialog;
    private TextView tvInvalid;
    private TextView tvNavigate;

    private SessionManager sessionManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the FirebaseAuth instance.
        mAuth = FirebaseAuth.getInstance();
        sessionManager = new SessionManager(getContext());

        progressDialogSettings();
    }
    private void progressDialogSettings() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Authenticating... ");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment
        getLayoutComponents(view);
        return view;
    }

    private void getLayoutComponents(View view) {
        btnLogin = view.findViewById(R.id.btn_login);
        etEmail = view.findViewById(R.id.et_mail);
        etPassword = view.findViewById(R.id.et_password);
        tvInvalid = view.findViewById(R.id.tv_invalid);
        tvNavigate = view.findViewById(R.id.tv_sign_up_navigate);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty())
                {
                    tvInvalid.setVisibility(View.VISIBLE);
                }else {
                    progressDialog.show();
                    createAccount(email, password);
                }
            }
        });
        tvNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateSignUpFragment();
            }
        });
    }

    private void createAccount(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            //Save to Shared Preference
                            sessionManager.createData(email, password);
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Wrong E-mail or Password", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void navigateSignUpFragment(){
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_layout, signUpFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
