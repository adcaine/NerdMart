package com.caine.allan.nerdmart.inject;

import android.content.Context;

import com.bignerdranch.android.nerdmartservice.service.NerdMartService;
import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;
import com.caine.allan.nerdmart.NerdMartServiceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allancaine on 2015-10-20.
 */

@Module
public class NerdMartApplicationModule {
    private Context mApplicationContext;

    public NerdMartApplicationModule(Context applicationContext){
        mApplicationContext = applicationContext;
    }

    @Provides
    NerdMartServiceInterface providesNerbMartServiceInterface(){
        return new NerdMartService();
    }

    @Provides
    NerdMartServiceManager providesNerdMartServiceManager(NerdMartServiceInterface nerdMartServiceInterface){
        return new NerdMartServiceManager(nerdMartServiceInterface);
    }
}
