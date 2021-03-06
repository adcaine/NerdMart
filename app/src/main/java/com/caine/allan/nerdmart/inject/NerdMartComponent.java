package com.caine.allan.nerdmart.inject;

import com.caine.allan.nerdmart.NerdMartAbstractActivity;
import com.caine.allan.nerdmart.NerdMartAbstractFragment;
import com.caine.allan.nerdmart.NerdMartViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by allancaine on 2015-10-20.
 */

@Singleton
@Component(modules = {NerdMartApplicationModule.class})
public interface NerdMartComponent {

    void inject(NerdMartAbstractActivity activity);
    void inject(NerdMartAbstractFragment fragment);
}
