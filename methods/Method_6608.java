public static String formatString(String key,int res,Object... args){
  try {
    String value=BuildVars.USE_CLOUD_STRINGS ? getInstance().localeValues.get(key) : null;
    if (value == null) {
      value=ApplicationLoader.applicationContext.getString(res);
    }
    if (getInstance().currentLocale != null) {
      return String.format(getInstance().currentLocale,value,args);
    }
 else {
      return String.format(value,args);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    return "LOC_ERR: " + key;
  }
}
