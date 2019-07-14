public boolean isProxyDialog(long did,boolean checkLeft){
  return proxyDialog != null && proxyDialog.id == did && (!checkLeft || isLeftProxyChannel);
}
