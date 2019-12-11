package com.example.nti.fragment.registerFragments;


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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nti.R;
import com.example.nti.mvp.SignUpUserView;
import com.example.nti.mvp.UserModelPresenter;
import com.example.nti.pojo.SessionManager;
import com.example.nti.pojo.UserModel;
import com.example.nti.ui.HomeActivity;

public class SignUpFragment extends Fragment implements SignUpUserView {

    private EditText etUserName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private TextView tvNavigate;
    private Button btnSignUp;
    private CheckBox checkBoxPolicy;

    private ProgressDialog progressDialog;

    private String confirmPassword;
    private UserModel userModel;

    private SessionManager sessionManager;
    private UserModelPresenter userModelPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the FirebaseAuth instance.
        sessionManager = new SessionManager(getContext());
        userModelPresenter = new UserModelPresenter(getContext(), this);
        userModel = new UserModel();
        progressDialogSettings();
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
        etUserName = view.findViewById(R.id.et_sign_up_username);
        etEmail = view.findViewById(R.id.et_sign_up_email);
        etPassword = view.findViewById(R.id.et_sign_up_password);
        etConfirmPassword = view.findViewById(R.id.et_sign_up_confirm_password);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        tvNavigate = view.findViewById(R.id.tv_login_navigate);
        checkBoxPolicy = view.findViewById(R.id.checkbox_policy);
    }

    private void progressDialogSettings() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Authenticating... ");
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
                setUserModelValue();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                if (userModelPresenter.isConfirmedPassword(userModel.getPassword(), confirmPassword)) {
                    progressDialog.show();
                    userModelPresenter.createUser(userModel);
                } else {
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

    private void setUserModelValue() {
        userModel.setName(etUserName.getText().toString());
        userModel.setEmail(etEmail.getText().toString());
        userModel.setPassword(etPassword.getText().toString().trim());
        userModel.setFavouriteMovie("All");
    }

    private void navigateLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_layout, loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateUser() {
        progressDialog.dismiss();
        sessionManager.createData(userModel.getEmail(), userModel.getPassword());
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }
}
