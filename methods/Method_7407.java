public static void setConfig(String json){
  try {
    config=new JSONObject(json);
    nativeSetConfig(json);
  }
 catch (  JSONException x) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Error parsing VoIP config",x);
    }
  }
}
