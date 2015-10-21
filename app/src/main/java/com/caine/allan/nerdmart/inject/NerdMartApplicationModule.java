package com.caine.allan.nerdmart.inject;

import android.content.Context;

import com.bignerdranch.android.nerdmartservice.service.NerdMartService;
import com.bignerdranch.android.nerdmartservice.service.NerdMartServiceInterface;
import com.caine.allan.nerdmart.DataStore;
import com.caine.allan.nerdmart.NerdMartServiceManager;

import javax.inject.Singleton;

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
    @Singleton
    DataStore providesDataStore(){
        return new DataStore();
    }

    @Provides
    NerdMartServiceInterface providesNerbMartServiceInterface(){
        return new NerdMartService();
    }

    @Provides
    NerdMartServiceManager providesNerdMartServiceManager(NerdMartServiceInterface nerdMartServiceInterface, DataStore dataStore){
        return new NerdMartServiceManager(nerdMartServiceInterface, dataStore);
    }
}
