/** 
 * Check if the application default shared preferences contains true for the key "acra.disable", do not activate ACRA. Also checks the alternative opposite setting "acra.enable" if "acra.disable" is not found.
 * @param prefs SharedPreferences to check to see whether ACRA should bedisabled.
 * @return true if prefs indicate that ACRA should be enabled.
 */
public static boolean shouldEnableACRA(@NonNull SharedPreferences prefs){
  boolean enableAcra=true;
  try {
    final boolean disableAcra=prefs.getBoolean(ACRA.PREF_DISABLE_ACRA,false);
    enableAcra=prefs.getBoolean(ACRA.PREF_ENABLE_ACRA,!disableAcra);
  }
 catch (  Exception e) {
  }
  return enableAcra;
}
