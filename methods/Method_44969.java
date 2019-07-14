public void saveConfig(){
  try {
    Preferences prefs=Preferences.userNodeForPackage(ConfigSaver.class);
    prefs.putBoolean(FLATTEN_SWITCH_BLOCKS_ID,decompilerSettings.getFlattenSwitchBlocks());
    prefs.putBoolean(FORCE_EXPLICIT_IMPORTS_ID,decompilerSettings.getForceExplicitImports());
    prefs.putBoolean(SHOW_SYNTHETIC_MEMBERS_ID,decompilerSettings.getShowSyntheticMembers());
    prefs.putBoolean(EXCLUDE_NESTED_TYPES_ID,decompilerSettings.getExcludeNestedTypes());
    prefs.putBoolean(FORCE_EXPLICIT_TYPE_ARGUMENTS_ID,decompilerSettings.getForceExplicitTypeArguments());
    prefs.putBoolean(RETAIN_REDUNDANT_CASTS_ID,decompilerSettings.getRetainRedundantCasts());
    prefs.putBoolean(INCLUDE_ERROR_DIAGNOSTICS_ID,decompilerSettings.getIncludeErrorDiagnostics());
    prefs.putBoolean(UNICODE_REPLACE_ENABLED_ID,decompilerSettings.isUnicodeOutputEnabled());
    prefs.put(LANGUAGE_NAME_ID,decompilerSettings.getLanguage().getName());
    saveWindowPosition(prefs,MAIN_WINDOW_ID_PREFIX,mainWindowPosition);
    saveWindowPosition(prefs,FIND_WINDOW_ID_PREFIX,findWindowPosition);
    saveLuytenPreferences(prefs);
  }
 catch (  Exception e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
}
