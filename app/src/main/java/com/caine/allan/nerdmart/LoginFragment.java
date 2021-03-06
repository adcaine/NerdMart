package com.caine.allan.nerdmart;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.caine.allan.nerdmart.databinding.FragmentLoginBinding;

import rx.Subscription;

/**
 * Created by allancaine on 2015-10-21.
 */
public class LoginFragment extends NerdMartAbstractFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        FragmentLoginBinding fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        fragmentLoginBinding.setLoginButtonClickListener(v -> {
            String username = fragmentLoginBinding.fragmentLoginUsername.getText().toString();
            String password = fragmentLoginBinding.fragmentLoginPassword.getText().toString();
            addSubscription(mNerdMartServiceManager
                    .authenticate(username, password)
                    .compose(loadingTransformer())
                    .subscribe(authenticated -> {
                        if(!authenticated){
                            Toast.makeText(getActivity(), R.string.auth_failure_toast, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getActivity(), R.string.auth_success_toast, Toast.LENGTH_SHORT).show();
                        Intent intent = ProductsActivity.newIntent(getActivity());
                        getActivity().finish();
                        startActivity(intent);
                    }));
        });


        return fragmentLoginBinding.getRoot();
    }



}
