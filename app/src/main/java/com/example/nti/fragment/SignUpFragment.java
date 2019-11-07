package com.example.nti.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class SignUpFragment extends Fragment {


    private FirebaseAuth mAuth;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvNavigate;
    private Button btnSignUp;
    private CheckBox checkBoxPolicy;

    private ProgressDialog progressDialog;

    private String email;
    private String password;
    private String confirmPassword;

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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        getLayoutComponents(view);
        return view;
    }

    private void getLayoutComponents(View view) {
        etEmail = view.findViewById(R.id.et_sign_up_email);
        etPassword = view.findViewById(R.id.et_sign_up_password);
        etConfirmPassword = view.findViewById(R.id.et_sign_up_confirm_password);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        tvNavigate = view.findViewById(R.id.tv_login_navigate);
        checkBoxPolicy = view.findViewById(R.id.checkbox_policy);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkBoxPolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnSignUp.setEnabled(true);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                if(isConfirmedPassword(password, confirmPassword)){
                    progressDialog.show();
                    createAccount(email, password);
                } else{
                    Toast.makeText(getContext(), "Password not matched", Toast.LENGTH_SHORT).show();
                    etPassword.setTextColor(getResources().getColor(R.color.hint));
                    etConfirmPassword.setTextColor(getResources().getColor(R.color.hint));
                }
            }
        });
        tvNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateLoginFragment();
            }
        });

    }
    private void createAccount(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            //Save to Shared Preference
                            sessionManager.createData(email, password);
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            startActivity(intent);
                        }else{
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void navigateLoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_layout, loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private boolean isConfirmedPassword(String password1, String password2){
        if(password1.equals(password2)){
            return true;
        }
        return false;
    }
}
