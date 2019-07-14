@Deprecated public static boolean getToggleValue(@NonNull String key,boolean defaultValue){
  return getPrefs().get(key,defaultValue);
}
