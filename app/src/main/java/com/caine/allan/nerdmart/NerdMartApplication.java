package com.caine.allan.nerdmart;

import android.app.Application;
import android.content.Context;

import com.caine.allan.nerdmart.inject.DaggerNerdMartComponent;
import com.caine.allan.nerdmart.inject.NerdMartApplicationModule;
import com.caine.allan.nerdmart.inject.NerdMartComponent;

import timber.log.Timber;

/**
 * Created by allancaine on 2015-10-20.
 */
public class NerdMartApplication extends Application{

    private NerdMartComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        mComponent = DaggerComponentInitializer.init(this);
    }

    public static NerdMartComponent component(Context context){
        return ((NerdMartApplication)context.getApplicationContext()).getComponent();
    }

    public NerdMartComponent getComponent(){
        return mComponent;
    }

    public final static class DaggerComponentInitializer {
        public static NerdMartComponent init(NerdMartApplication app){
            return DaggerNerdMartComponent.builder()
                    .nerdMartApplicationModule(new NerdMartApplicationModule(app))
                    .build();
        }
    }
}
