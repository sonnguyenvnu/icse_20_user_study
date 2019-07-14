public static String[] getCarrierInfo(){
  TelephonyManager tm=(TelephonyManager)ApplicationLoader.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
  if (Build.VERSION.SDK_INT >= 24) {
    tm=tm.createForSubscriptionId(SubscriptionManager.getDefaultDataSubscriptionId());
  }
  if (!TextUtils.isEmpty(tm.getNetworkOperatorName())) {
    String mnc="", mcc="";
    String carrierID=tm.getNetworkOperator();
    if (carrierID != null && carrierID.length() > 3) {
      mcc=carrierID.substring(0,3);
      mnc=carrierID.substring(3);
    }
    return new String[]{tm.getNetworkOperatorName(),tm.getNetworkCountryIso().toUpperCase(),mcc,mnc};
  }
  return null;
}
