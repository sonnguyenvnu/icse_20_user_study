/** 
 * Execute the request
 * @param context The context
 * @param networkManager The network manager to use
 */
public void execute(Context context,MmsNetworkManager networkManager){
  int result=SmsManager.MMS_ERROR_UNSPECIFIED;
  int httpStatusCode=0;
  byte[] response=null;
  WifiManager wifi=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
  boolean isWifiEnabled=wifi.isWifiEnabled();
  if (!useWifi(context)) {
    wifi.setWifiEnabled(false);
  }
  mobileDataEnabled=Utils.isMobileDataEnabled(context);
  Timber.v("mobile data enabled: " + mobileDataEnabled);
  if (!mobileDataEnabled && !useWifi(context)) {
    Timber.v("mobile data not enabled, so forcing it to enable");
    Utils.setMobileDataEnabled(context,true);
  }
  if (!ensureMmsConfigLoaded()) {
    Timber.e("MmsRequest: mms config is not loaded yet");
    result=SmsManager.MMS_ERROR_CONFIGURATION_ERROR;
  }
 else   if (!prepareForHttpRequest()) {
    Timber.e("MmsRequest: failed to prepare for request");
    result=SmsManager.MMS_ERROR_IO_ERROR;
  }
 else   if (!isDataNetworkAvailable(context,mSubId)) {
    Timber.e("MmsRequest: in airplane mode or mobile data disabled");
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
      result=SmsManager.MMS_ERROR_NO_DATA_NETWORK;
    }
 else {
      result=8;
    }
  }
 else {
    long retryDelaySecs=2;
    for (int i=0; i < RETRY_TIMES; i++) {
      try {
        try {
          networkManager.acquireNetwork();
        }
 catch (        Exception e) {
          Timber.e(e,"error acquiring network");
        }
        final String apnName=networkManager.getApnName();
        try {
          ApnSettings apn=null;
          try {
            apn=ApnSettings.load(context,apnName,mSubId);
          }
 catch (          ApnException e) {
            if (apnName == null) {
              throw (e);
            }
            Timber.i("MmsRequest: No match with APN name:" + apnName + ", try with no name");
            apn=ApnSettings.load(context,null,mSubId);
          }
          Timber.i("MmsRequest: using " + apn.toString());
          response=doHttp(context,networkManager,apn);
          result=Activity.RESULT_OK;
          break;
        }
  finally {
          networkManager.releaseNetwork();
        }
      }
 catch (      ApnException e) {
        Timber.e(e,"MmsRequest: APN failure");
        result=SmsManager.MMS_ERROR_INVALID_APN;
        break;
      }
catch (      MmsHttpException e) {
        Timber.e(e,"MmsRequest: HTTP or network I/O failure");
        result=SmsManager.MMS_ERROR_HTTP_FAILURE;
        httpStatusCode=e.getStatusCode();
      }
catch (      Exception e) {
        Timber.e(e,"MmsRequest: unexpected failure");
        result=SmsManager.MMS_ERROR_UNSPECIFIED;
        break;
      }
      try {
        Thread.sleep(retryDelaySecs * 1000,0);
      }
 catch (      InterruptedException e) {
      }
      retryDelaySecs<<=1;
    }
  }
  if (!mobileDataEnabled) {
    Timber.v("setting mobile data back to disabled");
    Utils.setMobileDataEnabled(context,false);
  }
  if (!useWifi(context)) {
    wifi.setWifiEnabled(isWifiEnabled);
  }
  processResult(context,result,response,httpStatusCode);
}
