package com.caine.allan.nerdmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;

import javax.inject.Inject;

/**
 * Created by allancaine on 2015-10-21.
 */
public abstract class NerdMartAbstractFragment extends Fragment {

    @Inject
    NerdMartServiceInterface mNerdMartServiceInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NerdMartApplication.component(getActivity()).inject(this);


    }
}
