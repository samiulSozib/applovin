package com.example.applovin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class MainActivity extends AppCompatActivity implements MaxAdListener {

    private MaxAdView adView;
    private MaxInterstitialAd interstitialAd;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button_send);

        AppLovinSdk.getInstance(MainActivity.this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(MainActivity.this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {

            }
        });

        adView = findViewById(R.id.adView);
        adView.loadAd();

        LoadInterstitialAd();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( interstitialAd.isReady() )
                {
                    interstitialAd.showAd();

                    //code here
                    LoadInterstitialAd();
                }
            }
        });



    }

    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {
        interstitialAd.loadAd();
    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

    }

    private void LoadInterstitialAd() {

        interstitialAd = new MaxInterstitialAd( "aba093c60d303809", this );
        interstitialAd.setListener( this );
        interstitialAd.loadAd();
    }
}