package com.example.chat.manager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.chat.base.ConstantUtil;
import com.example.chat.events.LocationEvent;
import com.example.commonlibrary.BaseApplication;
import com.example.commonlibrary.rxbus.RxBusManager;
import com.example.commonlibrary.utils.CommonLogger;

/**
 * é¡¹ç›®å??ç§°:    NewFastFrame
 * åˆ›å»ºäºº:      é™ˆé”¦å†›
 * åˆ›å»ºæ—¶é—´:    2018/3/25     21:46
 * QQ:         1981367757
 */

public class NewLocationManager implements AMapLocationListener {
    private static NewLocationManager instance;
    private AMapLocationClient mLocationClient = null;
    private double latitude;
    private double longitude;
    private String address;

    public static NewLocationManager getInstance() {
        if (instance == null) {
            instance = new NewLocationManager();
        }
        return instance;
    }


    public void startLocation() {
        if (mLocationClient == null) {
            CommonLogger.e("è¿™é‡Œæ–°å»ºå®šä½?");
            mLocationClient = new AMapLocationClient(BaseApplication.getInstance());
            mLocationClient.setLocationOption(getDefaultOption());
            mLocationClient.setLocationListener(this);
            mLocationClient.startLocation();
        }
    }


    private NewLocationManager() {
    }

    private AMapLocationClientOption getDefaultOption() {
        CommonLogger.e("èŽ·å?–å®šä½?é€‰é¡¹");
        AMapLocationClientOption option = new AMapLocationClientOption();
//          å®šä½?æ¨¡å¼?ï¼š1 é«˜ç²¾åº¦ã€?2ä»…è®¾å¤‡ã€?3ä»…ç½‘ç»œ
// è®¾ç½®é«˜ç²¾åº¦æ¨¡å¼?
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//                è®¾ç½®æ˜¯å?¦ä¼˜å…ˆä½¿ç”¨GPS
        option.setGpsFirst(false);
//                è¿žæŽ¥è¶…æ—¶3ç§’
        option.setHttpTimeOut(3000);
//                è®¾ç½®å®šä½?é—´éš”60ç§’
        option.setInterval(60000);
//                è®¾ç½®æ˜¯å?¦è¿”å›žåœ°å?€ï¼Œé»˜è®¤è¿”å›ž
        option.setNeedAddress(true);
//                è®¾ç½®æ˜¯å?¦å?•æ¬¡å®šä½?
        option.setOnceLocation(false);
        //å?¯é€‰ï¼Œè®¾ç½®æ˜¯å?¦ç­‰å¾…wifiåˆ·æ–°ï¼Œé»˜è®¤ä¸ºfalse.å¦‚æžœè®¾ç½®ä¸ºtrue,ä¼šè‡ªåŠ¨å?˜ä¸ºå?•æ¬¡å®šä½?ï¼ŒæŒ?ç»­å®šä½?æ—¶ä¸?è¦?ä½¿ç”¨
        option.setOnceLocationLatest(false);
//                è®¾ç½®ç½‘ç»œè¯·æ±‚å??è®®
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
//                è®¾ç½®æ˜¯å?¦ä½¿ç”¨ä¼ æ„Ÿå™¨,ä¸?ä½¿ç”¨
        option.setSensorEnable(false);
        return option;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                CommonLogger.e("1èŽ·å?–åˆ°ä½?ç½®ä¿¡æ?¯æ‹‰");
                //                èŽ·å?–çº¬åº¦
                if (latitude != aMapLocation.getLatitude() || longitude != aMapLocation.getLongitude()) {
                    latitude = aMapLocation.getLatitude();
                    //                èŽ·å?–ç»?åº¦
                    longitude = aMapLocation.getLongitude();
                    address = aMapLocation.getAddress();
                    CommonLogger.e(aMapLocation.toString());
//                                        aMapLocation.getLocationType();//èŽ·å?–å½“å‰?å®šä½?ç»“æžœæ?¥æº?
//                                        aMapLocation.getLatitude();//èŽ·å?–çº¬åº¦
//                                        aMapLocation.getLongitude();//èŽ·å?–ç»?åº¦
//                                        aMapLocation.getAccuracy();//èŽ·å?–ç²¾åº¦ä¿¡æ?¯
//                                        aMapLocation.getAddress();//åœ°å?€ï¼Œå¦‚æžœoptionä¸­è®¾ç½®isNeedAddressä¸ºfalseï¼Œåˆ™æ²¡æœ‰æ­¤ç»“æžœï¼Œç½‘ç»œå®šä½?ç»“æžœä¸­ä¼šæœ‰åœ°å?€ä¿¡æ?¯ï¼ŒGPSå®šä½?ä¸?è¿”å›žåœ°å?€ä¿¡æ?¯ã€‚
//                                        aMapLocation.getCountry();//å›½å®¶ä¿¡æ?¯
//                                        aMapLocation.getProvince();//çœ?ä¿¡æ?¯
//                                        aMapLocation.getCity();//åŸŽå¸‚ä¿¡æ?¯
//                                        aMapLocation.getDistrict();//åŸŽåŒºä¿¡æ?¯
//                                        aMapLocation.getStreet();//è¡—é?“ä¿¡æ?¯
//                                        aMapLocation.getStreetNum();//è¡—é?“é—¨ç‰Œå?·ä¿¡æ?¯
//                                        aMapLocation.getCityCode();//åŸŽå¸‚ç¼–ç ?
//                                        aMapLocation.getAdCode();//åœ°åŒºç¼–ç ?
//                                        aMapLocation.getAoiName();//èŽ·å?–å½“å‰?å®šä½?ç‚¹çš„AOIä¿¡æ?¯
                    LocationEvent locationEvent = new LocationEvent();
                    locationEvent.setLongitude(longitude);
                    locationEvent.setLatitude(latitude);
                    locationEvent.setLocation(address);
                    locationEvent.setCountry(aMapLocation.getCountry());
                    locationEvent.setProvince(aMapLocation.getProvince());
                    locationEvent.setCity(aMapLocation.getCity());
                    locationEvent.setTitle(aMapLocation.getPoiName());
                    RxBusManager.getInstance().post(locationEvent);
                    BaseApplication.getAppComponent().getSharedPreferences().edit().putString(ConstantUtil.LATITUDE, latitude+"")
                            .putString(ConstantUtil.LONGITUDE,longitude+"")
                            .putString(ConstantUtil.ADDRESS, address)
                    .putString(ConstantUtil.CITY,aMapLocation.getCity()).apply();
                    if (UserManager.getInstance().getCurrentUser() != null) {
                        UserManager.getInstance().updateUserInfo(ConstantUtil.LOCATION,longitude+"&"+latitude,null);
                    }
                } else {
                    CommonLogger.e("å®šä½?ç›¸å?Œ,ä¸?å®šä½?");
                }
            } else {
                CommonLogger.e("å‡ºé”™æ¶ˆæ?¯ï¼š" + aMapLocation.getErrorInfo() + "\n" + "é”™è¯¯ç ?:" + aMapLocation.getErrorCode() + "\n" + "é”™è¯¯çš„ç»†èŠ‚" +
                        aMapLocation.getLocationDetail());
            }
        }
    }


    public void clear() {
        CommonLogger.e("è¿™é‡Œæ¸…é™¤å®šä½?");
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
        longitude = 0;
        latitude = 0;
    }

}
