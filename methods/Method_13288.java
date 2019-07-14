protected static boolean getPreferenceValue(Map<String,String> preferences,String key,boolean defaultValue){
  String v=preferences.get(key);
  return (v == null) ? defaultValue : Boolean.valueOf(v);
}
