@ReactMethod public String getCarrier(){
  TelephonyManager telMgr=(TelephonyManager)this.reactContext.getSystemService(Context.TELEPHONY_SERVICE);
  return telMgr.getNetworkOperatorName();
}
