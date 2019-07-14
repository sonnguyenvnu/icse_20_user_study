/** 
 * @return The Shared Preferences where ACRA will retrieve its user adjustable setting.
 */
@NonNull public SharedPreferences create(){
  if (context == null) {
    throw new IllegalStateException("Cannot call ACRA.getACRASharedPreferences() before ACRA.init().");
  }
 else   if (!ACRAConstants.DEFAULT_STRING_VALUE.equals(config.sharedPreferencesName())) {
    return context.getSharedPreferences(config.sharedPreferencesName(),Context.MODE_PRIVATE);
  }
 else {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }
}
