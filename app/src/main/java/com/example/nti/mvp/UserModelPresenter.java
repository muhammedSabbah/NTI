package com.example.nti.mvp;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.nti.pojo.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserModelPresenter {

    private Context mContext;
    private SignUpUserView signUpUserView;
    private FirebaseAuth mAuth;
    private boolean IS_CREATED;

    public UserModelPresenter(Context mContext, SignUpUserView userView) {
        mAuth = FirebaseAuth.getInstance();
        this.mContext = mContext;
        this.signUpUserView = userView;
        IS_CREATED = true;
    }

    public void createUser(UserModel userModel) {
        if (createUserWithAuthentication(userModel)) {
            signUpUserView.onCreateUser();
        }
    }

    private boolean createUserWithAuthentication(final UserModel userModel) {
        mAuth.createUserWithEmailAndPassword(userModel.getEmail(), userModel.getPassword())
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            IS_CREATED = true;
                        } else {
                            IS_CREATED = false;
                        }
                    }
                });
        return IS_CREATED;
    }


    public boolean isConfirmedPassword(String password1, String password2) {
        if (password1.equals(password2)) {
            return true;
        }
        return false;
    }
}
