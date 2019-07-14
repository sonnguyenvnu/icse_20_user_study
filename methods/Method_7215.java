public static void loadProxyList(){
  if (proxyListLoaded) {
    return;
  }
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("mainconfig",Activity.MODE_PRIVATE);
  String proxyAddress=preferences.getString("proxy_ip","");
  String proxyUsername=preferences.getString("proxy_user","");
  String proxyPassword=preferences.getString("proxy_pass","");
  String proxySecret=preferences.getString("proxy_secret","");
  int proxyPort=preferences.getInt("proxy_port",1080);
  proxyListLoaded=true;
  proxyList.clear();
  currentProxy=null;
  String list=preferences.getString("proxy_list",null);
  if (!TextUtils.isEmpty(list)) {
    byte[] bytes=Base64.decode(list,Base64.DEFAULT);
    SerializedData data=new SerializedData(bytes);
    int count=data.readInt32(false);
    for (int a=0; a < count; a++) {
      ProxyInfo info=new ProxyInfo(data.readString(false),data.readInt32(false),data.readString(false),data.readString(false),data.readString(false));
      proxyList.add(info);
      if (currentProxy == null && !TextUtils.isEmpty(proxyAddress)) {
        if (proxyAddress.equals(info.address) && proxyPort == info.port && proxyUsername.equals(info.username) && proxyPassword.equals(info.password)) {
          currentProxy=info;
        }
      }
    }
    data.cleanup();
  }
  if (currentProxy == null && !TextUtils.isEmpty(proxyAddress)) {
    ProxyInfo info=currentProxy=new ProxyInfo(proxyAddress,proxyPort,proxyUsername,proxyPassword,proxySecret);
    proxyList.add(0,info);
  }
}
