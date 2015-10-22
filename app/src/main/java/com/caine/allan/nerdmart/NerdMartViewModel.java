package com.caine.allan.nerdmart;

import android.content.Context;

import com.bignerdranch.android.nerdmartservice.service.payload.Cart;
import com.bignerdranch.android.nerdmartservice.service.payload.User;

/**
 * Created by allancaine on 2015-10-21.
 */
public class NerdMartViewModel {
    private Context mContext;
    private Cart mCart;
    private User mUser;


    public NerdMartViewModel(Context context, Cart cart, User user){
        mContext = context;
        mCart = cart;
        mUser = user;
    }

    public String formatCartItemsDisplay() {
        int numItems = 0;
        if(mCart != null && mCart.getProducts() != null){
            numItems = mCart.getProducts().size();
        }

        return mContext.getResources().getQuantityString(R.plurals.cart, numItems, numItems);
    }

    public String getUserGreeting() {
        return mContext.getString(R.string.user_greeting, mUser.getName());
    }

    public String getCartDisplay(){
        return formatCartItemsDisplay();
    }

    public void updateCartStatus(Cart cart){
        mCart = cart;
    }

}
