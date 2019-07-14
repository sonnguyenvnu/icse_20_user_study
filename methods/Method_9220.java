private void updateProxyButton(boolean animated){
  if (proxyDrawable == null) {
    return;
  }
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("mainconfig",Activity.MODE_PRIVATE);
  String proxyAddress=preferences.getString("proxy_ip","");
  boolean proxyEnabled;
  if ((proxyEnabled=preferences.getBoolean("proxy_enabled",false) && !TextUtils.isEmpty(proxyAddress)) || getMessagesController().blockedCountry && !SharedConfig.proxyList.isEmpty()) {
    if (!actionBar.isSearchFieldVisible()) {
      proxyItem.setVisibility(View.VISIBLE);
    }
    proxyDrawable.setConnected(proxyEnabled,currentConnectionState == ConnectionsManager.ConnectionStateConnected || currentConnectionState == ConnectionsManager.ConnectionStateUpdating,animated);
    proxyItemVisisble=true;
  }
 else {
    proxyItem.setVisibility(View.GONE);
    proxyItemVisisble=false;
  }
}
