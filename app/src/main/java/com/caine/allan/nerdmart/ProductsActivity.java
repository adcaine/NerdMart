package com.caine.allan.nerdmart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import dagger.Component;
import timber.log.Timber;

public class ProductsActivity extends NerdMartAbstractActivity {

    @Override
    protected Fragment getFragment() {
        return new ProductsFragment();
    }

    public static Intent newIntent(Context context){
        return new Intent(context, ProductsActivity.class);
    }
}
