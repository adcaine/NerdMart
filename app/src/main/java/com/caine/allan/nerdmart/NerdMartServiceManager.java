package com.caine.allan.nerdmart;

import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;
import com.bignerdranch.android.nerdmartservice.service.payload.Product;
import com.bignerdranch.android.nerdmartservice.service.payload.User;

import java.util.UUID;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by allancaine on 2015-10-21.
 */
public class NerdMartServiceManager {

    private NerdMartServiceInterface mNerdMartServiceInterface;
    private DataStore mDataStore;

    public NerdMartServiceManager(NerdMartServiceInterface nerdMartServiceInterface, DataStore dataStore){
        mNerdMartServiceInterface = nerdMartServiceInterface;
        mDataStore = dataStore;
    }

    public Observable<Boolean> authenticate(String username, String password){
        return mNerdMartServiceInterface.authenticate(username, password)
                .doOnNext(mDataStore::setCachedUser)
                .map(user -> user != null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<UUID> getToken(){
        return Observable.just(mDataStore.getCachedAuthToken());
    }

    public Observable<Product> getProducts(){
        return getToken().flatMap(mNerdMartServiceInterface::requestProducts)
                .doOnNext(mDataStore::setCachedProducts)
                .flatMap(Observable::from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
