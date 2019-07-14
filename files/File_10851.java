package com.vondear.rxtool;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.vondear.rxtool.model.Gps;
import com.vondear.rxtool.view.RxToast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author vondear
 * @date 2016/11/13
 * @desc å®šä½?ç›¸å…³å·¥å…·ç±»
 */
public class RxLocationTool {

    //åœ†å‘¨çŽ‡
    public static double pi = 3.1415926535897932384626;
    //Krasovsky 1940 (åŒ—äº¬54)æ¤­ç?ƒé•¿å?Šè½´
    public static double a = 6378245.0;
    //æ¤­ç?ƒçš„å??å¿ƒçŽ‡
    public static double ee = 0.00669342162296594323;
    private static OnLocationChangeListener mListener;
    private static MyLocationListener myLocationListener;
    private static LocationManager mLocationManager;

    /**
     * åˆ¤æ–­Gpsæ˜¯å?¦å?¯ç”¨
     *
     * @return {@code true}: æ˜¯<br>{@code false}: å?¦
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * åˆ¤æ–­å®šä½?æ˜¯å?¦å?¯ç”¨
     *
     * @return {@code true}: æ˜¯<br>{@code false}: å?¦
     */
    public static boolean isLocationEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * æ‰“å¼€Gpsè®¾ç½®ç•Œé?¢
     */
    public static void openGpsSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * æ³¨å†Œ
     * <p>ä½¿ç”¨å®Œè®°å¾—è°ƒç”¨{@link #unRegisterLocation()}</p>
     * <p>éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     * <p>éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>}</p>
     * <p>éœ€æ·»åŠ æ?ƒé™? {@code <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>}</p>
     * <p>å¦‚æžœ{@code minDistance}ä¸º0ï¼Œåˆ™é€šè¿‡{@code minTime}æ?¥å®šæ—¶æ›´æ–°ï¼›</p>
     * <p>{@code minDistance}ä¸?ä¸º0ï¼Œåˆ™ä»¥{@code minDistance}ä¸ºå‡†ï¼›</p>
     * <p>ä¸¤è€…éƒ½ä¸º0ï¼Œåˆ™éš?æ—¶åˆ·æ–°ã€‚</p>
     *
     * @param minTime     ä½?ç½®ä¿¡æ?¯æ›´æ–°å‘¨æœŸï¼ˆå?•ä½?ï¼šæ¯«ç§’ï¼‰
     * @param minDistance ä½?ç½®å?˜åŒ–æœ€å°?è·?ç¦»ï¼šå½“ä½?ç½®è·?ç¦»å?˜åŒ–è¶…è¿‡æ­¤å€¼æ—¶ï¼Œå°†æ›´æ–°ä½?ç½®ä¿¡æ?¯ï¼ˆå?•ä½?ï¼šç±³ï¼‰
     * @param listener    ä½?ç½®åˆ·æ–°çš„å›žè°ƒæŽ¥å?£
     * @return {@code true}: åˆ?å§‹åŒ–æˆ?åŠŸ<br>{@code false}: åˆ?å§‹åŒ–å¤±è´¥
     */
    public static boolean registerLocation(Context context, long minTime, long minDistance, OnLocationChangeListener listener) {
        if (listener == null) {
            return false;
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return false;
        }
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mListener = listener;
        if (!isLocationEnabled(context)) {
            RxToast.showToast(context, "æ— æ³•å®šä½?ï¼Œè¯·æ‰“å¼€å®šä½?æœ?åŠ¡", 500);
            return false;
        }
        String provider = mLocationManager.getBestProvider(getCriteria(), true);

        Location location = mLocationManager.getLastKnownLocation(provider);
        if (location != null) {
            listener.getLastKnownLocation(location);
        }
        if (myLocationListener == null) {
            myLocationListener = new MyLocationListener();
        }
        mLocationManager.requestLocationUpdates(provider, minTime, minDistance, myLocationListener);
        return true;
    }

    /**
     * æ³¨é”€
     */
    public static void unRegisterLocation() {
        if (mLocationManager != null) {
            if (myLocationListener != null) {
                mLocationManager.removeUpdates(myLocationListener);
                myLocationListener = null;
            }
            mLocationManager = null;
        }
    }

    /**
     * è®¾ç½®å®šä½?å?‚æ•°
     *
     * @return {@link Criteria}
     */
    private static Criteria getCriteria() {
        Criteria criteria = new Criteria();
        //è®¾ç½®å®šä½?ç²¾ç¡®åº¦ Criteria.ACCURACY_COARSEæ¯”è¾ƒç²—ç•¥ï¼ŒCriteria.ACCURACY_FINEåˆ™æ¯”è¾ƒç²¾ç»†
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //è®¾ç½®æ˜¯å?¦è¦?æ±‚é€Ÿåº¦
        criteria.setSpeedRequired(true);
        // è®¾ç½®æ˜¯å?¦å…?è®¸è¿?è?¥å•†æ”¶è´¹
        criteria.setCostAllowed(true);
        //è®¾ç½®æ˜¯å?¦éœ€è¦?æ–¹ä½?ä¿¡æ?¯
        criteria.setBearingRequired(true);
        //è®¾ç½®æ˜¯å?¦éœ€è¦?æµ·æ‹”ä¿¡æ?¯
        criteria.setAltitudeRequired(true);
        // è®¾ç½®å¯¹ç”µæº?çš„éœ€æ±‚
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    /**
     * æ ¹æ?®ç»?çº¬åº¦èŽ·å?–åœ°ç?†ä½?ç½®
     *
     * @param context   ä¸Šä¸‹æ–‡
     * @param latitude  çº¬åº¦
     * @param longitude ç»?åº¦
     * @return {@link Address}
     */
    public static Address getAddress(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                return addresses.get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * æ ¹æ?®ç»?çº¬åº¦èŽ·å?–æ‰€åœ¨å›½å®¶
     *
     * @param context   ä¸Šä¸‹æ–‡
     * @param latitude  çº¬åº¦
     * @param longitude ç»?åº¦
     * @return æ‰€åœ¨å›½å®¶
     */
    public static String getCountryName(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);
        return address == null ? "unknown" : address.getCountryName();
    }

    /**
     * æ ¹æ?®ç»?çº¬åº¦èŽ·å?–æ‰€åœ¨åœ°
     *
     * @param context   ä¸Šä¸‹æ–‡
     * @param latitude  çº¬åº¦
     * @param longitude ç»?åº¦
     * @return æ‰€åœ¨åœ°
     */
    public static String getLocality(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);
        return address == null ? "unknown" : address.getLocality();
    }

    /**
     * æ ¹æ?®ç»?çº¬åº¦èŽ·å?–æ‰€åœ¨è¡—é?“
     *
     * @param context   ä¸Šä¸‹æ–‡
     * @param latitude  çº¬åº¦
     * @param longitude ç»?åº¦
     * @return æ‰€åœ¨è¡—é?“
     */
    public static String getStreet(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);
        return address == null ? "unknown" : address.getAddressLine(0);
    }

    //------------------------------------------å??æ ‡è½¬æ?¢å·¥å…·start--------------------------------------

    /**
     * GPSå??æ ‡ è½¬æ?¢æˆ? è§’åº¦
     * ä¾‹å¦‚ 113.202222 è½¬æ?¢æˆ? 113Â°12â€²8â€³
     *
     * @param location
     * @return
     */
    public static String gpsToDegree(double location) {
        double degree = Math.floor(location);
        double minute_temp = (location - degree) * 60;
        double minute = Math.floor(minute_temp);
//        double second = Math.floor((minute_temp - minute)*60);
        String second = new DecimalFormat("#.##").format((minute_temp - minute) * 60);
        return (int) degree + "Â°" + (int) minute + "â€²" + second + "â€³";
    }

    /**
     * å›½é™… GPS84 å??æ ‡ç³»
     * è½¬æ?¢æˆ?
     * [å›½æµ‹å±€å??æ ‡ç³»] ç?«æ˜Ÿå??æ ‡ç³» (GCJ-02)
     * <p>
     * World Geodetic System ==> Mars Geodetic System
     *
     * @param lon ç»?åº¦
     * @param lat çº¬åº¦
     * @return GPSå®žä½“ç±»
     */
    public static Gps GPS84ToGCJ02(double lon, double lat) {
        if (outOfChina(lon, lat)) {
            return null;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLon, mgLat);
    }

    /**
     * [å›½æµ‹å±€å??æ ‡ç³»] ç?«æ˜Ÿå??æ ‡ç³» (GCJ-02)
     * è½¬æ?¢æˆ?
     * å›½é™… GPS84 å??æ ‡ç³»
     *
     * @param lon ç?«æ˜Ÿç»?åº¦
     * @param lat ç?«æ˜Ÿçº¬åº¦
     */
    public static Gps GCJ02ToGPS84(double lon, double lat) {
        Gps gps = transform(lon, lat);
        double lontitude = lon * 2 - gps.getLongitude();
        double latitude = lat * 2 - gps.getLatitude();
        return new Gps(lontitude, latitude);
    }

    /**
     * ç?«æ˜Ÿå??æ ‡ç³» (GCJ-02)
     * è½¬æ?¢æˆ?
     * ç™¾åº¦å??æ ‡ç³» (BD-09)
     *
     * @param ggLon ç»?åº¦
     * @param ggLat çº¬åº¦
     */
    public static Gps GCJ02ToBD09(double ggLon, double ggLat) {
        double x = ggLon, y = ggLat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bdLon = z * Math.cos(theta) + 0.0065;
        double bdLat = z * Math.sin(theta) + 0.006;
        return new Gps(bdLon, bdLat);
    }

    /**
     * ç™¾åº¦å??æ ‡ç³» (BD-09)
     * è½¬æ?¢æˆ?
     * ç?«æ˜Ÿå??æ ‡ç³» (GCJ-02)
     *
     * @param bdLon ç™¾åº¦*ç»?åº¦
     * @param bdLat ç™¾åº¦*çº¬åº¦
     * @return GPSå®žä½“ç±»
     */
    public static Gps BD09ToGCJ02(double bdLon, double bdLat) {
        double x = bdLon - 0.0065, y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double ggLon = z * Math.cos(theta);
        double ggLat = z * Math.sin(theta);
        return new Gps(ggLon, ggLat);
    }

    /**
     * ç™¾åº¦å??æ ‡ç³» (BD-09)
     * è½¬æ?¢æˆ?
     * å›½é™… GPS84 å??æ ‡ç³»
     *
     * @param bdLon ç™¾åº¦*ç»?åº¦
     * @param bdLat ç™¾åº¦*çº¬åº¦
     * @return GPSå®žä½“ç±»
     */
    public static Gps BD09ToGPS84(double bdLon, double bdLat) {
        Gps gcj02 = BD09ToGCJ02(bdLon, bdLat);
        Gps map84 = GCJ02ToGPS84(gcj02.getLongitude(), gcj02.getLatitude());
        return map84;

    }

    /**
     * å›½é™… GPS84 å??æ ‡ç³»
     * è½¬æ?¢æˆ?
     * ç™¾åº¦å??æ ‡ç³» (BD-09)
     *
     * @param gpsLon  å›½é™… GPS84 å??æ ‡ç³»ä¸‹ çš„ç»?åº¦
     * @param gpsLat  å›½é™… GPS84 å??æ ‡ç³»ä¸‹ çš„çº¬åº¦
     * @return ç™¾åº¦GPSå??æ ‡
     */
    public static Gps GPS84ToBD09(double gpsLon, double gpsLat) {
        Gps gcj02 = GPS84ToGCJ02(gpsLon, gpsLat);
        Gps bd09 = GCJ02ToBD09(gcj02.getLongitude(), gcj02.getLatitude());
        return bd09;
    }

    /**
     * ä¸?åœ¨ä¸­å›½èŒƒå›´å†…
     *
     * @param lon ç»?åº¦
     * @param lat çº¬åº¦
     * @return booleanå€¼
     */
    public static boolean outOfChina(double lon, double lat) {
        return lon < 72.004 || lon > 137.8347 || lat < 0.8293 || lat > 55.8271;
    }

    /**
     * è½¬åŒ–ç®—æ³•
     *
     * @param lon ç»?åº¦
     * @param lat çº¬åº¦
     * @return  GPSä¿¡æ?¯
     */
    private static Gps transform(double lon, double lat) {
        if (outOfChina(lon, lat)) {
            return new Gps(lon, lat);
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLon, mgLat);
    }

    /**
     * çº¬åº¦è½¬åŒ–ç®—æ³•
     *
     * @param x xå??æ ‡
     * @param y yå??æ ‡
     * @return  çº¬åº¦
     */
    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * ç»?åº¦è½¬åŒ–ç®—æ³•
     *
     * @param x  xå??æ ‡
     * @param y  yå??æ ‡
     * @return ç»?åº¦
     */
    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    public interface OnLocationChangeListener {

        /**
         * èŽ·å?–æœ€å?Žä¸€æ¬¡ä¿?ç•™çš„å??æ ‡
         *
         * @param location å??æ ‡
         */
        void getLastKnownLocation(Location location);

        /**
         * å½“å??æ ‡æ”¹å?˜æ—¶è§¦å?‘æ­¤å‡½æ•°ï¼Œå¦‚æžœProviderä¼ è¿›ç›¸å?Œçš„å??æ ‡ï¼Œå®ƒå°±ä¸?ä¼šè¢«è§¦å?‘
         *
         * @param location å??æ ‡
         */
        void onLocationChanged(Location location);

        /**
         * providerçš„åœ¨å?¯ç”¨ã€?æš‚æ—¶ä¸?å?¯ç”¨å’Œæ— æœ?åŠ¡ä¸‰ä¸ªçŠ¶æ€?ç›´æŽ¥åˆ‡æ?¢æ—¶è§¦å?‘æ­¤å‡½æ•°
         *
         * @param provider æ??ä¾›è€…
         * @param status   çŠ¶æ€?
         * @param extras   providerå?¯é€‰åŒ…
         */
        void onStatusChanged(String provider, int status, Bundle extras);//ä½?ç½®çŠ¶æ€?å?‘ç”Ÿæ”¹å?˜
    }

    private static class MyLocationListener implements LocationListener {
        /**
         * å½“å??æ ‡æ”¹å?˜æ—¶è§¦å?‘æ­¤å‡½æ•°ï¼Œå¦‚æžœProviderä¼ è¿›ç›¸å?Œçš„å??æ ‡ï¼Œå®ƒå°±ä¸?ä¼šè¢«è§¦å?‘
         *
         * @param location å??æ ‡
         */
        @Override
        public void onLocationChanged(Location location) {
            if (mListener != null) {
                mListener.onLocationChanged(location);
            }
        }

        /**
         * providerçš„åœ¨å?¯ç”¨ã€?æš‚æ—¶ä¸?å?¯ç”¨å’Œæ— æœ?åŠ¡ä¸‰ä¸ªçŠ¶æ€?ç›´æŽ¥åˆ‡æ?¢æ—¶è§¦å?‘æ­¤å‡½æ•°
         *
         * @param provider æ??ä¾›è€…
         * @param status   çŠ¶æ€?
         * @param extras   providerå?¯é€‰åŒ…
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (mListener != null) {
                mListener.onStatusChanged(provider, status, extras);
            }
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("onStatusChanged", "å½“å‰?GPSçŠ¶æ€?ä¸ºå?¯è§?çŠ¶æ€?");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("onStatusChanged", "å½“å‰?GPSçŠ¶æ€?ä¸ºæœ?åŠ¡åŒºå¤–çŠ¶æ€?");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("onStatusChanged", "å½“å‰?GPSçŠ¶æ€?ä¸ºæš‚å?œæœ?åŠ¡çŠ¶æ€?");
                    break;
                default:
                    break;
            }
        }

        /**
         * providerè¢«enableæ—¶è§¦å?‘æ­¤å‡½æ•°ï¼Œæ¯”å¦‚GPSè¢«æ‰“å¼€
         */
        @Override
        public void onProviderEnabled(String provider) {
        }

        /**
         * providerè¢«disableæ—¶è§¦å?‘æ­¤å‡½æ•°ï¼Œæ¯”å¦‚GPSè¢«å…³é—­
         */
        @Override
        public void onProviderDisabled(String provider) {
        }
    }
    //===========================================å??æ ‡è½¬æ?¢å·¥å…·end====================================

    public static boolean isMove(Location location, Location preLocation) {
        boolean isMove;
        if (preLocation != null) {
            double speed = location.getSpeed() * 3.6;
            double distance = location.distanceTo(preLocation);
            double compass = Math.abs(preLocation.getBearing() - location.getBearing());
            double angle;
            if (compass > 180) {
                angle = 360 - compass;
            } else {
                angle = compass;
            }
            if (speed != 0) {
                if (speed < 35 && (distance > 3 && distance < 10)) {
                    isMove = angle > 10;
                } else {
                    isMove = (speed < 40 && distance > 10 && distance < 100) ||
                            (speed < 50 && distance > 10 && distance < 100) ||
                            (speed < 60 && distance > 10 && distance < 100) ||
                            (speed < 9999 && distance > 100);
                }
            } else {
                isMove = false;
            }
        } else {
            isMove = true;
        }
        return isMove;
    }
}
