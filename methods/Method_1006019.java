public static BasePanelPreferences from(JabRefPreferences preferences){
  BasePanelPreferences basePanelPreferences=new BasePanelPreferences(preferences.getMainTablePreferences(),preferences.getAutoCompletePreferences(),preferences.getEntryEditorPreferences(),Globals.getKeyPrefs(),preferences.getPreviewPreferences(),preferences.getDouble(JabRefPreferences.ENTRY_EDITOR_HEIGHT));
  EasyBind.subscribe(basePanelPreferences.entryEditorDividerPosition,value -> preferences.putDouble(JabRefPreferences.ENTRY_EDITOR_HEIGHT,value.doubleValue()));
  return basePanelPreferences;
}
