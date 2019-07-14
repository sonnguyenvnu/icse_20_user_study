public void updateToCurrentVersionIfNecessary(){
  if (!prefs.getBoolean(PREF__LEGACY_ALREADY_CONVERTED_TO_4_8_0,false)) {
    new ReportMigrator(context).migrate();
    prefs.edit().putBoolean(PREF__LEGACY_ALREADY_CONVERTED_TO_4_8_0,true).apply();
  }
  if (!prefs.getBoolean(PREF__LEGACY_ALREADY_CONVERTED_TO_JSON,false)) {
    new ReportConverter(context).convert();
    prefs.edit().putBoolean(PREF__LEGACY_ALREADY_CONVERTED_TO_JSON,true).apply();
  }
}
