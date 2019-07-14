/** 
 * ??????
 * @param context
 * @return
 */
public static String getDeviceInfo(Context context){
  try {
    org.json.JSONObject json=new org.json.JSONObject();
    TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    String device_id=null;
    if (checkPermission(context,Manifest.permission.READ_PHONE_STATE)) {
      device_id=getDeviceIdIMEI(context);
    }
    String mac=null;
    FileReader fstream=null;
    try {
      fstream=new FileReader("/sys/class/net/wlan0/address");
    }
 catch (    FileNotFoundException e) {
      fstream=new FileReader("/sys/class/net/eth0/address");
    }
    BufferedReader in=null;
    if (fstream != null) {
      try {
        in=new BufferedReader(fstream,1024);
        mac=in.readLine();
      }
 catch (      IOException e) {
      }
 finally {
        if (fstream != null) {
          try {
            fstream.close();
          }
 catch (          IOException e) {
            e.printStackTrace();
          }
        }
        if (in != null) {
          try {
            in.close();
          }
 catch (          IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    json.put("mac",mac);
    if (TextUtils.isEmpty(device_id)) {
      device_id=mac;
    }
    if (TextUtils.isEmpty(device_id)) {
      device_id=Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);
    }
    json.put("device_id",device_id);
    return json.toString();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
