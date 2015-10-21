package com.caine.allan.nerdmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;

import javax.inject.Inject;

/**
 * Created by allancaine on 2015-10-20.
 */
public abstract class NerdMartAbstractActivity extends AppCompatActivity {

    @Inject
    NerdMartServiceInterface mNerdMartServiceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NerdMartApplication.component(this).inject(this);

        setContentView(R.layout.activity_abstract_nerdmart);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_abstract_nerdmart_fragment_frame, getFragment())
                    .commit();
        }


    }

    protected abstract Fragment getFragment();
}
