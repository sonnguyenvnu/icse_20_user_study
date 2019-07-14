public static boolean isEnabledWifiHotspot(Context context){
  WifiManager wm=(WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
  Boolean enabled=callIsWifiApEnabled(wm);
  return enabled != null ? enabled : false;
}
