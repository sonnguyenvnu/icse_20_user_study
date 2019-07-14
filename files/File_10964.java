package com.vondear.rxui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.vondear.rxtool.RxLocationTool;
import com.vondear.rxtool.RxVibrateTool;
import com.vondear.rxtool.model.Gps;
import com.vondear.rxtool.view.RxToast;
import com.vondear.rxui.view.dialog.RxDialogGPSCheck;
import com.vondear.rxui.view.dialog.RxDialogTool;


/**
 * @author vondear
 */
public abstract class ActivityBaseLocation extends ActivityBase {

    //ç»?åº¦
    public double mLongitude = 0;
    //çº¬åº¦
    public double mLatitude = 0;

    //GPSä¿¡æ?¯
    public Gps mGps;

    public LocationManager mLocationManager;
    private LocationListener mLocationListener;

    public abstract void setGpsInfo(Location location);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //åˆ?å§‹åŒ–GPS
        initGPS();
        //GPSå¼€å?¯çŠ¶æ€?æ£€æµ‹
        gpsCheck();
    }

    private void initGPS() {
        mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
    }

    //----------------------------------------------------------------------------------------------æ£€æµ‹GPSæ˜¯å?¦å·²æ‰“å¼€ start
    protected void gpsCheck() {
        if (!RxLocationTool.isGpsEnabled(this)) {
            RxDialogGPSCheck rxDialogGPSCheck = new RxDialogGPSCheck(mContext);
            rxDialogGPSCheck.show();
        } else {
            getLocation();
        }
    }
    //==============================================================================================æ£€æµ‹GPSæ˜¯å?¦å·²æ‰“å¼€ end

    @SuppressLint("MissingPermission")
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            RxDialogTool.initDialogSurePermission(mContext, "è¯·å…ˆæ‰“å¼€GPSå®šä½?æ?ƒé™?");
            return;
        }
        mLocationListener = new LocationListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onLocationChanged(Location location) {
                mLongitude = location.getLongitude();
                mLatitude = location.getLatitude();
                mGps = new Gps(mLongitude, mLatitude);
                setGpsInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                switch (status) {
                    //GPSçŠ¶æ€?ä¸ºå?¯è§?æ—¶
                    case LocationProvider.AVAILABLE:
//                        RxToast.normal("å½“å‰?GPSæœ?åŠ¡å·²æ?¢å¤?");
                        break;
                    //GPSçŠ¶æ€?ä¸ºæœ?åŠ¡åŒºå¤–æ—¶
                    case LocationProvider.OUT_OF_SERVICE:
                        RxToast.normal("å½“å‰?GPSä¿¡å?·å¼±");
                        RxVibrateTool.vibrateOnce(mContext, 3000);
                        break;
                    //GPSçŠ¶æ€?ä¸ºæš‚å?œæœ?åŠ¡æ—¶
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        RxToast.normal("å½“å‰?GPSå·²æš‚å?œæœ?åŠ¡");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                RxToast.normal("å½“å‰?GPSè®¾å¤‡å·²æ‰“å¼€");
                RxVibrateTool.vibrateOnce(mContext, 800);
            }

            @Override
            public void onProviderDisabled(String provider) {
                RxToast.normal("å½“å‰?GPSè®¾å¤‡å·²å…³é—­");
                RxVibrateTool.vibrateOnce(mContext, 800);
                gpsCheck();
            }
        };

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationListener != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }
    }
}
