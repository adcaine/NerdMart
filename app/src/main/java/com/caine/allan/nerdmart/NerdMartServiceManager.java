package com.caine.allan.nerdmart;

import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;
import com.bignerdranch.android.nerdmartservice.service.payload.User;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by allancaine on 2015-10-21.
 */
public class NerdMartServiceManager {

    private NerdMartServiceInterface mNerdMartServiceInterface;

    public NerdMartServiceManager(NerdMartServiceInterface nerdMartServiceInterface){
        mNerdMartServiceInterface = nerdMartServiceInterface;
    }

    public Observable<Boolean> authenticate(String username, String password){
        return mNerdMartServiceInterface.authenticate(username, password)
                .map(user -> user != null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
