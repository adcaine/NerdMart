package com.caine.allan.nerdmart;

import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;
import com.bignerdranch.android.nerdmartservice.service.payload.Cart;
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

    private final Observable.Transformer<Observable, Observable> mSchedulersTransform =
            observable -> observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());

    public NerdMartServiceManager(NerdMartServiceInterface nerdMartServiceInterface, DataStore dataStore){
        mNerdMartServiceInterface = nerdMartServiceInterface;
        mDataStore = dataStore;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers(){
        return (Observable.Transformer<T, T>) mSchedulersTransform;
    }

    public Observable<Boolean> authenticate(String username, String password){
        return mNerdMartServiceInterface.authenticate(username, password)
                .doOnNext(mDataStore::setCachedUser)
                .map(user -> user != null)
                .compose(applySchedulers());
    }

    private Observable<UUID> getToken(){
        return Observable.just(mDataStore.getCachedAuthToken());
    }

    public Observable<Product> getProducts(){
        return getToken().flatMap(mNerdMartServiceInterface::requestProducts)
                .doOnNext(mDataStore::setCachedProducts)
                .flatMap(Observable::from)
                .compose(applySchedulers());
    }

    public Observable<Cart> getCart() {
        return getToken().flatMap(mNerdMartServiceInterface::fetchUserCart)
                .doOnNext(mDataStore::setCachedCart)
                .compose(applySchedulers());
    }

    public Observable<Boolean> postProductToCart(final Product product){
        return getToken()
                .flatMap(uuid -> mNerdMartServiceInterface.addProductToCart(uuid, product))
                .compose(applySchedulers());
    }

    public Observable<Boolean> signout() {
        mDataStore.clearCache();
        return mNerdMartServiceInterface.signout();
    }



}
