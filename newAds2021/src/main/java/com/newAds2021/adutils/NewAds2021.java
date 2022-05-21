package com.newAds2021.adutils;

import android.app.Application;
//
//import com.applovin.sdk.AppLovinSdk;
//import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.newAds2021.adsmodels.AdsPrefernce;

public class NewAds2021 extends Application {
    private static AppOpenManager appOpenManager;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {}
                });
        AudienceNetworkAds.initialize(this);
//        AudienceNetworkAds.initialize(getApplicationContext());
        if (new AdsPrefernce(this).appRunCount() != 1){
            appOpenManager = new AppOpenManager(this);
        }



    }
}
