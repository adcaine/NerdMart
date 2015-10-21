package com.caine.allan.nerdmart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caine.allan.nerdmart.databinding.FragmentProductBinding;

import timber.log.Timber;

/**
 * Created by allancaine on 2015-10-21.
 */
public class ProductsFragment extends NerdMartAbstractFragment {

    private FragmentProductBinding mFragmentProductBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mFragmentProductBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        updateUI();
        return mFragmentProductBinding.getRoot();
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
