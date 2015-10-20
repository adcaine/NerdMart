package com.caine.allan.nerdmart.inject;

import com.caine.allan.nerdmart.NerdMartAbstractActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by allancaine on 2015-10-20.
 */

@Singleton
@Component(modules = {NerdMartApplicationModule.class})
public interface NerdMartComponent {

    void inject(NerdMartAbstractActivity activity);
}
