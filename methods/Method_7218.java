public static void deleteProxy(ProxyInfo proxyInfo){
  if (currentProxy == proxyInfo) {
    currentProxy=null;
    SharedPreferences preferences=MessagesController.getGlobalMainSettings();
    boolean enabled=preferences.getBoolean("proxy_enabled",false);
    SharedPreferences.Editor editor=preferences.edit();
    editor.putString("proxy_ip","");
    editor.putString("proxy_pass","");
    editor.putString("proxy_user","");
    editor.putString("proxy_secret","");
    editor.putInt("proxy_port",1080);
    editor.putBoolean("proxy_enabled",false);
    editor.putBoolean("proxy_enabled_calls",false);
    editor.commit();
    if (enabled) {
      ConnectionsManager.setProxySettings(false,"",0,"","","");
    }
  }
  proxyList.remove(proxyInfo);
  saveProxyList();
}
