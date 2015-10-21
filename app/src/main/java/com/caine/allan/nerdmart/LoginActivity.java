package com.caine.allan.nerdmart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by allancaine on 2015-10-21.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_login_fragment_frame, new LoginFragment())
                    .commit();
        }
    }
}
