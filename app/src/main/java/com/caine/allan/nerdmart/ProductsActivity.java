package com.caine.allan.nerdmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import timber.log.Timber;

public class ProductsActivity extends NerdMartAbstractActivity {

    @Override
    protected Fragment getFragment() {
        return new ProductsFragment();
    }
}
