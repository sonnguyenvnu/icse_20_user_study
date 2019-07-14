public static boolean isValidUrl(String text){
  return !TextUtils.isEmpty(text) && PatternsCompat.WEB_URL.matcher(text).matches();
}
