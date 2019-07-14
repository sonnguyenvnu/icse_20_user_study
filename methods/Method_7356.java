public static int[] getWifiInfo(){
  try {
    WifiManager wmgr=(WifiManager)ApplicationLoader.applicationContext.getSystemService(Context.WIFI_SERVICE);
    WifiInfo info=wmgr.getConnectionInfo();
    return new int[]{info.getRssi(),info.getLinkSpeed()};
  }
 catch (  Exception ignore) {
  }
  return null;
}
