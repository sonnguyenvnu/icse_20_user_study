public static void saveProxyList(){
  SerializedData serializedData=new SerializedData();
  int count=proxyList.size();
  serializedData.writeInt32(count);
  for (int a=0; a < count; a++) {
    ProxyInfo info=proxyList.get(a);
    serializedData.writeString(info.address != null ? info.address : "");
    serializedData.writeInt32(info.port);
    serializedData.writeString(info.username != null ? info.username : "");
    serializedData.writeString(info.password != null ? info.password : "");
    serializedData.writeString(info.secret != null ? info.secret : "");
  }
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("mainconfig",Activity.MODE_PRIVATE);
  preferences.edit().putString("proxy_list",Base64.encodeToString(serializedData.toByteArray(),Base64.NO_WRAP)).commit();
  serializedData.cleanup();
}
