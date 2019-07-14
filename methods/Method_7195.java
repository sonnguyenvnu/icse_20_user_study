public static HashMap<String,String> getCountryLangs(){
  if (passportConfigMap == null) {
    passportConfigMap=new HashMap<>();
    try {
      JSONObject object=new JSONObject(passportConfigJson);
      Iterator<String> iter=object.keys();
      while (iter.hasNext()) {
        String key=iter.next();
        passportConfigMap.put(key.toUpperCase(),object.getString(key).toUpperCase());
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
  return passportConfigMap;
}
