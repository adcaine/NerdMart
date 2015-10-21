package com.caine.allan.nerdmart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import timber.log.Timber;

/**
 * Created by allancaine on 2015-10-21.
 */
public class ProductsFragment extends NerdMartAbstractFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragments_product, container, false);
        updateUI();
        return view;
    }

    private void updateUI(){
        addSubscription(
            mNerdMartServiceManager
                    .getProducts()
                    .compose(loadingTransformer())
                    .subscribe(product -> {
                       Timber.i("received products: " + product);
                }));
    }
}
