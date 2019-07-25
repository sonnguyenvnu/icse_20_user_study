package com.xuexiang.xuidemo.utils;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.xuexiang.xaop.annotation.Permission;
import com.xuexiang.xui.logs.UILog;

import static com.xuexiang.xaop.consts.PermissionConsts.LOCATION;

/**
 * 定�?�?务
 *
 * @author xuexiang
 * @since 2019/3/31 下�?�5:52
 */
public class LocationService {

    private static volatile LocationService sInstance = null;

    private LocationClient mClient = null;
    private LocationClientOption mOption, mDIYOption;

    private LocationService() {

    }

    /***
     * �?始化
     * @param context
     */
    public void init(Context context) {
        if (mClient == null) {
            mClient = new LocationClient(context.getApplicationContext());
            mClient.setLocOption(getDefaultLocationClientOption());
        }
    }

    /**
     * 获�?��?�例
     *
     * @return
     */
    public static LocationService get() {
        if (sInstance == null) {
            synchronized (LocationService.class) {
                if (sInstance == null) {
                    sInstance = new LocationService();
                }
            }
        }
        return sInstance;
    }

    /***
     * 注册定�?监�?�
     * @param listener
     * @return
     */

    public LocationService registerListener(BDAbstractLocationListener listener) {
        if (listener != null) {
            mClient.registerLocationListener(listener);
        }
        return this;
    }

    /**
     * 注销定�?监�?�
     *
     * @param listener
     */
    public LocationService unregisterListener(BDAbstractLocationListener listener) {
        if (listener != null) {
            mClient.unRegisterLocationListener(listener);
        }
        return this;
    }

    /***
     * 设置定�?�?�数
     * @param option
     * @return
     */
    public boolean setLocationOption(LocationClientOption option) {
        if (option != null) {
            if (mClient.isStarted()) {
                mClient.stop();
            }
            mDIYOption = option;
            mClient.setLocOption(option);
            return true;
        }
        return false;
    }

    /***
     *
     * @return DefaultLocationClientOption  默认O设置
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationMode.Hight_Accuracy);//�?�选，默认高精度，设置定�?模�?，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");//�?�选，默认gcj02，设置返回的定�?结果�??标系，如果�?�?�百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(0);//�?�选，默认0，�?�仅定�?一次，设置�?�起连续定�?请求的间隔需�?大于等于1000ms�?是有效的
            mOption.setIsNeedAddress(true);//�?�选，设置是�?�需�?地�?�信�?�，默认�?需�?
            mOption.setIsNeedLocationDescribe(true);//�?�选，设置是�?�需�?地�?��??述
            mOption.setNeedDeviceDirect(false);//�?�选，设置是�?�需�?设备方�?�结果
            mOption.setLocationNotify(false);//�?�选，默认false，设置是�?�当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true);//�?�选，默认true，定�?SDK内部是一个SERVICE，并放到了独立进程，设置是�?�在stop的时候�?�死这个进程，默认�?�?�死
            mOption.setIsNeedLocationDescribe(true);//�?�选，默认false，设置是�?�需�?�?置语义化结果，�?�以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近�?
            mOption.setIsNeedLocationPoiList(true);//�?�选，默认false，设置是�?�需�?POI结果，�?�以在BDLocation.getPoiList里得到
            mOption.SetIgnoreCacheException(false);//�?�选，默认false，设置是�?�收集CRASH信�?�，默认收集
            mOption.setOpenGps(true);//�?�选，默认false，设置是�?�开�?�Gps定�?
            mOption.setIsNeedAltitude(false);//�?�选，默认false，设置定�?时是�?�需�?海拔信�?�，默认�?需�?，除基础定�?版本都�?�用

        }
        return mOption;
    }


    /**
     * @return DIYOption 自定义Option设置
     */
    public LocationClientOption getOption() {
        if (mDIYOption == null) {
            mDIYOption = new LocationClientOption();
        }
        return mDIYOption;
    }

    /**
     * 开始定�?
     *
     * @param listener
     */
    @Permission(LOCATION)
    public static void start(BDAbstractLocationListener listener) {
        get().registerListener(listener).start();
    }

    /**
     * 开始定�?
     */
    public LocationService start() {
        if (mClient != null && !mClient.isStarted()) {
            mClient.start();
        }
        return this;
    }

    /**
     * �?�止定�?
     */
    public static void stop(BDAbstractLocationListener listener) {
        get().unregisterListener(listener).stop();
    }

    /**
     * �?�止定�?
     */
    public LocationService stop() {
        if (mClient != null && mClient.isStarted()) {
            mClient.stop();
        }
        return this;
    }

    public boolean isStart() {
        return mClient.isStarted();
    }

    public boolean requestHotSpotState() {
        return mClient.requestHotSpotState();
    }


    /**
     * 打�?�地�?�信�?�
     *
     * @param location
     */
    public static void printLocationInfo(BDLocation location) {
        if (null != location && location.getLocType() != BDLocation.TypeServerError) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            /**
             * 时间也�?�以使用systemClock.elapsedRealtime()方法 获�?�的是自从开机以�?�，�?次回调的时间；
             * location.getTime() 是指�?务端出本次结果的时间，如果�?置�?�?�生�?�化，则时间�?�?�
             */
            sb.append(location.getTime());
            sb.append("\nlocType : ");// 定�?类型
            sb.append(location.getLocType());
            sb.append("\nlocType description : ");// *****对应的定�?类型说明*****
            sb.append(location.getLocTypeDescription());
            sb.append("\nlatitude : ");// 纬度
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");// �?度
            sb.append(location.getLongitude());
            sb.append("\nradius : ");// �?�径
            sb.append(location.getRadius());
            sb.append("\nCountryCode : ");// 国家�?
            sb.append(location.getCountryCode());
            sb.append("\nCountry : ");// 国家�??称
            sb.append(location.getCountry());
            sb.append("\ncitycode : ");// 城市编�?
            sb.append(location.getCityCode());
            sb.append("\ncity : ");// 城市
            sb.append(location.getCity());
            sb.append("\nDistrict : ");// 区
            sb.append(location.getDistrict());
            sb.append("\nStreet : ");// 街�?�
            sb.append(location.getStreet());
            sb.append("\naddr : ");// 地�?�信�?�
            sb.append(location.getAddrStr());
            sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
            sb.append(location.getUserIndoorState());
            sb.append("\nDirection(not all devices have value): ");
            sb.append(location.getDirection());// 方�?�
            sb.append("\nlocationdescribe: ");
            sb.append(location.getLocationDescribe());// �?置语义化信�?�
            sb.append("\nPoi: ");// POI信�?�
            if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                for (int i = 0; i < location.getPoiList().size(); i++) {
                    Poi poi = location.getPoiList().get(i);
                    sb.append(poi.getName() + ";");
                }
            }
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定�?结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 速度 �?��?：km/h
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());// �?�星数目
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 海拔高度 �?��?：米
                sb.append("\ngps status : ");
                sb.append(location.getGpsAccuracyStatus());// *****gps质�?判断*****
                sb.append("\ndescribe : ");
                sb.append("gps定�?�?功");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定�?结果
                // �?�?�商信�?�
                if (location.hasAltitude()) {// *****如果有海拔高度*****
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// �?��?：米
                }
                sb.append("\noperationers : ");// �?�?�商信�?�
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定�?�?功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定�?结果
                sb.append("\ndescribe : ");
                sb.append("离线定�?�?功，离线定�?结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("�?务端网络定�?失败，�?�以�??馈IMEI�?�和大体定�?时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络�?�?�导致定�?失败，请检查网络是�?�通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获�?�有效定�?�?�?�导致定�?失败，一般是由于手机的原因，处于飞行模�?下一般会造�?这�?结果，�?�以试�?��?�?�手机");
            }
            UILog.i(sb.toString());
        }
    }


}
