public static String getAppConfig(String key){
  if (RESOURCE.containsKey(key)) {
    return RESOURCE.getString(key);
  }
  return null;
}
