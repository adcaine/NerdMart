package com.caine.allan.nerdmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by allancaine on 2015-10-21.
 */
public class LoginFragment extends NerdMartAbstractFragment {

    @Bind(R.id.fragment_login_username)
    EditText mUsernameEditText;

    @Bind(R.id.fragment_login_password)
    EditText mPasswordEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_login_login_button)
    public void handleLoginClick(){
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        Subscription subscription = mNerdMartServiceManager
                .authenticate(username, password)
                .subscribe(authenticated -> {
                    Toast.makeText(getActivity(), R.string.auth_success_toast, Toast.LENGTH_SHORT).show();
                    Intent intent = ProductsActivity.newIntent(getActivity());
                    startActivity(intent);
                    getActivity().finish();
                });
    }
}