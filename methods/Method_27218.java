public static boolean showWhatsNew(){
  return PrefHelper.getInt(WHATS_NEW_VERSION) != BuildConfig.VERSION_CODE;
}
