package com.caine.allan.nerdmart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.nerdmartservice.service.payload.Product;
import com.caine.allan.nerdmart.databinding.FragmentProductBinding;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by allancaine on 2015-10-21.
 */
public class ProductsFragment extends NerdMartAbstractFragment {

    private FragmentProductBinding mFragmentProductBinding;
    private ProductRecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mFragmentProductBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        ProductRecyclerViewAdapter.AddProductClickEvent addProductClickEvent = this::postProductToCart;
        mAdapter = new ProductRecyclerViewAdapter(new ArrayList<>(), getActivity(), addProductClickEvent);
        setupAdapter();
        updateUI();
        return mFragmentProductBinding.getRoot();
    }

    private void setupAdapter(){
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity());
        mFragmentProductBinding.fragmentProductsRecyclerView.setLayoutManager(linearLayoutManager);
        mFragmentProductBinding.fragmentProductsRecyclerView.setAdapter(mAdapter);
    }

    private void updateUI(){
        addSubscription(
                mNerdMartServiceManager
                        .getProducts()
                        .compose(loadingTransformer())
                        .subscribe(product -> {
                            Timber.i("received products: " + product);
                            mAdapter.setProducts(product);
                            mAdapter.notifyDataSetChanged();
                        }));
    }

    private void postProductToCart(Product product){

    }
}
