package com.vondear.rxtool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import com.vondear.rxtool.view.RxToast;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 设备工具类
 *
 * @author vondear
 * @date 2016/1/24
 */

public class RxDeviceTool {

    /**
     * 得到�?幕的高
     *
     * @param context 实体
     * @return 设备�?幕的高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 得到�?幕的宽
     *
     * @param context 实体
     * @return 设备�?幕的宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 得到设备�?幕的宽度
     */
    public static int getScreenWidths(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备�?幕的高度
     */
    public static int getScreenHeights(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备的密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * 获�?�手机唯一标识�?列�?�
     *
     * @return 手机唯一标识�?列�?�
     */
    public static String getUniqueSerialNumber() {
        //�?牌类型 例如： Galaxy nexus
        String phoneName = Build.MODEL;
        //�?牌 例如：samsung
        String manuFacturer = Build.MANUFACTURER;
        Log.d("详细�?列�?�", manuFacturer + "-" + phoneName + "-" + getSerialNumber());
        return manuFacturer + "-" + phoneName + "-" + getSerialNumber();
    }

    /**
     * IMEI （唯一标识�?列�?�）
     * <p>需与{@link #isPhone(Context)}一起使用</p>
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @param context 上下文
     * @return IMEI
     */
    public static String getIMEI(Context context) {
        String deviceId;
        if (isPhone(context)) {
            deviceId = getDeviceIdIMEI(context);
        } else {
            deviceId = getAndroidId(context);
        }
        return deviceId;
    }

    /**
     * 获�?�设备的IMSI
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        return getSubscriberId(context);
    }

    /**
     * 获�?�设备的IMEI
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceIdIMEI(Context context) {
        String id;
        //android.telephony.TelephonyManager
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备�?��?");
            return null;
        }
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            //android.provider.Settings;
            id = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }

    /**
     * 获�?�设备的软件版本�?�
     *
     * @param context
     * @return
     */
    public static String getDeviceSoftwareVersion(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备�?��?");
            return null;
        }
        return tm.getDeviceSoftwareVersion();
    }

    /**
     * 获�?�手机�?�
     *
     * @param context
     * @return
     */
    public static String getLine1Number(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备�?��?");
            return null;
        }
        return tm.getLine1Number();
    }

    /**
     * 获�?�ISO标准的国家�?，�?�国际长途区�?�
     *
     * @param context
     * @return
     */
    public static String getNetworkCountryIso(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

    /**
     * 获�?�设备的 MCC + MNC
     *
     * @param context
     * @return
     */
    public static String getNetworkOperator(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperator();
    }

    /**
     * 获�?�(当�?已注册的用户)的�??字
     *
     * @param context
     * @return
     */
    public static String getNetworkOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName();
    }

    /**
     * 获�?�当�?使用的网络类型
     *
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkType();
    }

    /**
     * 获�?�手机类型
     *
     * @param context
     * @return
     */
    public static int getPhoneType(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType();
    }

    /**
     * 获�?�SIM�?�的国家�?
     *
     * @param context
     * @return
     */
    public static String getSimCountryIso(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimCountryIso();
    }

    /**
     * 获�?�SIM�?��??供的移动国家�?和移动网络�?.5或6�?的�??进制数字
     *
     * @param context
     * @return
     */
    public static String getSimOperator(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperator();
    }

    /**
     * 获�?��?务商�??称
     *
     * @param context
     * @return
     */
    public static String getSimOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperatorName();
    }

    /**
     * 获�?�SIM�?�的�?列�?�
     *
     * @param context
     * @return
     */
    public static String getSimSerialNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备信�?��?��?");
            return null;
        }
        return tm.getSimSerialNumber();
    }

    /**
     * 获�?�SIM的状�?信�?�
     *
     * @param context
     * @return
     */
    public static int getSimState(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimState();
    }

    /**
     * 获�?�唯一的用户ID
     *
     * @param context
     * @return
     */
    public static String getSubscriberId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备信�?��?��?");
            return null;
        }
        return tm.getSubscriberId();
    }

    /**
     * 获�?�语音邮件�?��?
     *
     * @param context
     * @return
     */
    public static String getVoiceMailNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备信�?��?��?");
            return null;
        }
        return tm.getVoiceMailNumber();
    }

    /**
     * 获�?�ANDROID ID
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获�?�设备型�?�，如MI2SC
     *
     * @return 设备型�?�
     */
    public static String getBuildBrandModel() {
        return Build.MODEL;// Galaxy nexus �?牌类型
    }

    public static String getBuildBrand() {
        return Build.BRAND;//google
    }

    /**
     * 获�?�设备厂商，如Xiaomi
     *
     * @return 设备厂商
     */
    public static String getBuildMANUFACTURER() {
        return Build.MANUFACTURER;// samsung �?牌
    }

    /**
     * �?列�?�
     *
     * @return
     */
    public static String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }

    public static String getAppPackageName() {
        return RxTool.getContext().getPackageName();
    }

    /**
     * 获�?�App版本�??称
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        // 获�?�packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当�?类的包�??，0代表是获�?�版本信�?�
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 获�?�App版本�?�
     *
     * @param context
     * @return
     */
    public static int getAppVersionNo(Context context) {
        // 获�?�packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当�?类的包�??，0代表是获�?�版本信�?�
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int version = packInfo.versionCode;
        return version;
    }

    /**
     * 检查�?��?
     *
     * @param context
     * @param permission 例如 Manifest.permission.READ_PHONE_STATE
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                result = rest == PackageManager.PERMISSION_GRANTED;
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 获�?�设备信�?�
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = getDeviceIdIMEI(context);
            }
            String mac = null;
            FileReader fstream = null;
            try {
                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                fstream = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (fstream != null) {
                try {
                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();
                } catch (IOException e) {
                } finally {
                    if (fstream != null) {
                        try {
                            fstream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * �??历LOG输出HashMap
     *
     * @param res
     */
    public static void ThroughArray(HashMap res) {
        Iterator ite = res.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry entry = (Map.Entry) ite.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Log.d("MSG_AUTH_COMPLETE", (key + "： " + value));
        }
    }


    /**
     * 获�?�设备MAC地�?�
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @param context 上下文
     * @return MAC地�?�
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info != null) {
            String macAddress = info.getMacAddress();
            if (macAddress != null) {
                return macAddress.replace(":", "");
            }
        }
        return null;
    }

    /**
     * 获�?�设备MAC地�?�
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return MAC地�?�
     */

    public static String getMacAddress() {
        String macAddress = null;
        LineNumberReader lnr = null;
        InputStreamReader isr = null;
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            isr = new InputStreamReader(pp.getInputStream());
            lnr = new LineNumberReader(isr);
            macAddress = lnr.readLine().replace(":", "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            RxFileTool.closeIO(lnr, isr);
        }
        return macAddress == null ? "" : macAddress;
    }


    /**
     * 判断设备是�?�是手机
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isPhone(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }


    /**
     * 获�?�手机状�?信�?�
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @param context 上下文
     * @return DeviceId(IMEI) = 99000311726612<br>
     * DeviceSoftwareVersion = 00<br>
     * Line1Number =<br>
     * NetworkCountryIso = cn<br>
     * NetworkOperator = 46003<br>
     * NetworkOperatorName = 中国电信<br>
     * NetworkType = 6<br>
     * honeType = 2<br>
     * SimCountryIso = cn<br>
     * SimOperator = 46003<br>
     * SimOperatorName = 中国电信<br>
     * SimSerialNumber = 89860315045710604022<br>
     * SimState = 5<br>
     * SubscriberId(IMSI) = 460030419724900<br>
     * VoiceMailNumber = *86<br>
     */
    public static String getPhoneStatus(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            RxToast.error("请先获�?�读�?�手机设备信�?��?��?");
            return null;
        }
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String str = "";
        str += "DeviceId(IMEI) = " + getDeviceIdIMEI(context) + "\n";
        str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n";
        str += "Line1Number = " + tm.getLine1Number() + "\n";
        str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
        str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
        str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
        str += "NetworkType = " + tm.getNetworkType() + "\n";
        str += "honeType = " + tm.getPhoneType() + "\n";
        str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
        str += "SimOperator = " + tm.getSimOperator() + "\n";
        str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
        str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
        str += "SimState = " + tm.getSimState() + "\n";
        str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
        str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
        return str;
    }

    /**
     * 跳至填充好phoneNumber的拨�?�界�?�
     *
     * @param context     上下文
     * @param phoneNumber 电�?�?��?
     */
    public static void dial(Context context, String phoneNumber) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }

    /**
     * 拨打电�?
     * 需添加�?��? {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}
     *
     * @param context     上下文
     * @param phoneNumber 电�?�?��?
     */
    public static void callPhone(final Context context, String phoneNumber) {
        if (!RxDataTool.isNullString(phoneNumber)) {
            final String phoneNumber1 = phoneNumber.trim();// 删除字符串首部和尾部的空格
            // 调用系统的拨�?��?务实现电�?拨打功能
            // �?装一个拨打电�?的intent，并且将电�?�?��?包装�?一个Uri对象传入

            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber1));
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            context.startActivity(intent);// 内部类
        }
    }

    /**
     * �?��?短信
     *
     * @param context     上下文
     * @param phoneNumber 电�?�?��?
     * @param content     内容
     */
    public static void sendSms(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + (RxDataTool.isNullString(phoneNumber) ? "" : phoneNumber));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", RxDataTool.isNullString(content) ? "" : content);
        context.startActivity(intent);
    }

    /**
     * 获�?�手机�?�系人
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>}</p>
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.READ_CONTACTS"/>}</p>
     *
     * @param context 上下文;
     * @return �?�系人链表
     */
    public static List<HashMap<String, String>> getAllContactInfo(Context context) {
        SystemClock.sleep(3000);
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        // 1.获�?�内容解�?者
        ContentResolver resolver = context.getContentResolver();
        // 2.获�?�内容�??供者的地�?�:com.android.contacts
        // raw_contacts表的地�?� :raw_contacts
        // view_data表的地�?� : data
        // 3.生�?查询地�?�
        Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri date_uri = Uri.parse("content://com.android.contacts/data");
        // 4.查询�?作,先查询raw_contacts,查询contact_id
        // projection : 查询的字段
        Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"},
                null, null, null);
        // 5.解�?cursor
        while (cursor.moveToNext()) {
            // 6.获�?�查询的数�?�
            String contact_id = cursor.getString(0);
            // cursor.getString(cursor.getColumnIndex("contact_id"));//getColumnIndex
            // : 查询字段在cursor中索引值,一般都是用在查询字段比较多的时候
            // 判断contact_id是�?�为空
            if (!RxDataTool.isNullString(contact_id)) {//null   ""
                // 7.根�?�contact_id查询view_data表中的数�?�
                // selection : 查询�?�件
                // selectionArgs :查询�?�件的�?�数
                // sortOrder : 排�?
                // 空指针: 1.null.方法 2.�?�数为null
                Cursor c = resolver.query(date_uri, new String[]{"data1",
                                "mimetype"}, "raw_contact_id=?",
                        new String[]{contact_id}, null);
                HashMap<String, String> map = new HashMap<String, String>();
                // 8.解�?c
                while (c.moveToNext()) {
                    // 9.获�?�数�?�
                    String data1 = c.getString(0);
                    String mimetype = c.getString(1);
                    // 10.根�?�类型去判断获�?�的data1数�?�并�?存
                    if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                        // 电�?
                        map.put("phone", data1);
                    } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                        // 姓�??
                        map.put("name", data1);
                    }
                }
                // 11.添加到集�?�中数�?�
                list.add(map);
                // 12.关闭cursor
                c.close();
            }
        }
        // 12.关闭cursor
        cursor.close();
        return list;
    }

    /**
     * 打开手机�?�系人界�?�点击�?�系人�?�便获�?�该�?��?
     * <p>�?�照以下注释代�?</p>
     */
    public static void getContantNum(Activity context) {
        Log.i("tips", "U should copy the following code.");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        context.startActivityForResult(intent, 0);

        /*@Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (data != null) {
                Uri uri = data.getData();
                String num = null;
                // 创建内容解�?者
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(uri,
                        null, null, null, null);
                while (cursor.moveToNext()) {
                    num = cursor.getString(cursor.getColumnIndex("data1"));
                }
                cursor.close();
                num = num.replaceAll("-", "");//替�?�的�?作,555-6 -> 5556
            }
        }*/
    }

    /**
     * 获�?�手机短信并�?存到xml中
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.READ_SMS"/>}</p>
     * <p>需添加�?��? {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @param context 上下文
     */
    public static void getAllSMS(Context context) {
        // 1.获�?�短信
        // 1.1获�?�内容解�?者
        ContentResolver resolver = context.getContentResolver();
        // 1.2获�?�内容�??供者地�?�   sms,sms表的地�?�:null  �?写
        // 1.3获�?�查询路径
        Uri uri = Uri.parse("content://sms");
        // 1.4.查询�?作
        // projection : 查询的字段
        // selection : 查询的�?�件
        // selectionArgs : 查询�?�件的�?�数
        // sortOrder : 排�?
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
        // 设置最大进度
        int count = cursor.getCount();//获�?�短信的个数
        // 2.备份短信
        // 2.1获�?�xml�?列器
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            // 2.2设置xml文件�?存的路径
            // os : �?存的�?置
            // encoding : 编�?格�?
            xmlSerializer.setOutput(new FileOutputStream(new File("/mnt/sdcard/backupsms.xml")), "utf-8");
            // 2.3设置头信�?�
            // standalone : 是�?�独立�?存
            xmlSerializer.startDocument("utf-8", true);
            // 2.4设置根标签
            xmlSerializer.startTag(null, "smss");
            // 1.5.解�?cursor
            while (cursor.moveToNext()) {
                SystemClock.sleep(1000);
                // 2.5设置短信的标签
                xmlSerializer.startTag(null, "sms");
                // 2.6设置文本内容的标签
                xmlSerializer.startTag(null, "address");
                String address = cursor.getString(0);
                // 2.7设置文本内容
                xmlSerializer.text(address);
                xmlSerializer.endTag(null, "address");
                xmlSerializer.startTag(null, "date");
                String date = cursor.getString(1);
                xmlSerializer.text(date);
                xmlSerializer.endTag(null, "date");
                xmlSerializer.startTag(null, "type");
                String type = cursor.getString(2);
                xmlSerializer.text(type);
                xmlSerializer.endTag(null, "type");
                xmlSerializer.startTag(null, "body");
                String body = cursor.getString(3);
                xmlSerializer.text(body);
                xmlSerializer.endTag(null, "body");
                xmlSerializer.endTag(null, "sms");
                System.out.println("address:" + address + "   date:" + date + "  type:" + type + "  body:" + body);
            }
            xmlSerializer.endTag(null, "smss");
            xmlSerializer.endDocument();
            // 2.8将数�?�刷新到文件中
            xmlSerializer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置�?幕为横�?
     * <p>还有一�?就是在Activity中加属性android:screenOrientation="landscape"</p>
     * <p>�?设置Activity的android:configChanges时，切�?会�?新调用�?�个生命周期，切横�?时会执行一次，切竖�?时会执行两次</p>
     * <p>设置Activity的android:configChanges="orientation"时，切�?还是会�?新调用�?�个生命周期，切横�?竖�?时�?�会执行一次</p>
     * <p>设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"（4.0以上必须带最�?�一个�?�数）时
     * 切�?�?会�?新调用�?�个生命周期，�?�会执行onConfigurationChanged方法</p>
     *
     * @param activity activity
     */
    public static void setLandscape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 设置�?幕为竖�?
     *
     * @param activity activity
     */
    public static void setPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 判断是�?�横�?
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 判断是�?�竖�?
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 获�?��?幕旋转角度
     *
     * @param activity activity
     * @return �?幕旋转角度
     */
    public static int getScreenRotation(Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            default:
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
    }

    /**
     * 获�?�当�?�?幕截图，包�?�状�?�?
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap captureWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap ret = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return ret;
    }

    /**
     * 获�?�当�?�?幕截图，�?包�?�状�?�?
     * <p>需�?用到上�?�获�?�状�?�?高度getStatusBarHeight的方法</p>
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap captureWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int statusBarHeight = RxBarTool.getStatusBarHeight(activity);
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap ret = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return ret;
    }

    /**
     * 获�?�DisplayMetrics对象
     *
     * @param context 应用程�?上下文
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 判断是�?��?�?
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public static boolean isScreenLock(Context context) {
        KeyguardManager km = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();
    }


    /**
     * 设置安全窗�?�，�?用系统截�?。防止 App 中的一些界�?�被截�?，并显示在其他设备中造�?信�?�泄�?。
     * （常�?手机设备系统截�?�?作方�?为：�?�时按下电�?键和音�?键。）
     *
     * @param activity
     */
    public static void noScreenshots(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }
}
