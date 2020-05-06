package com.color.sms.messages.theme.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.color.sms.messages.theme.BuildConfig;
import com.color.sms.messages.theme.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public abstract class BaseAdsActivity extends AppCompatActivity {
    private static InterstitialAd mInterstitialAd;
    private static int timeShowAds = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        loadInterstitialAds();
    }

    private void loadInterstitialAds() {
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getInterstitialAdsId());
            mInterstitialAd.loadAd(getBuildRequest());
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mInterstitialAd.loadAd(getBuildRequest());
                }
            });
        }
    }

    public void showAds() {
        if (timeShowAds % 2 == 1) {
            if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }
        timeShowAds++;
    }

    public void forceShowAds() {
        timeShowAds = 1;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        timeShowAds++;
    }


    private AdRequest getBuildRequest() {
        if (BuildConfig.DEBUG) {
            return new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        } else {
            return new AdRequest.Builder().build();
        }
    }

    private String getInterstitialAdsId() {
        if (BuildConfig.DEBUG) {
            return getString(R.string.admob_interstitial_test);
        } else return getString(R.string.admob_interstitial_1);
    }
}
