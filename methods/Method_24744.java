public void ensurePrefsExist(){
  if (Preferences.get(prefErrorCheck) == null)   Preferences.setBoolean(prefErrorCheck,errorCheckEnabled);
  if (Preferences.get(prefWarnings) == null)   Preferences.setBoolean(prefWarnings,warningsEnabled);
  if (Preferences.get(COMPLETION_PREF) == null)   Preferences.setBoolean(COMPLETION_PREF,codeCompletionsEnabled);
  if (Preferences.get(prefDebugOP) == null)   if (Preferences.get(prefErrorLogs) == null)   Preferences.setBoolean(prefErrorLogs,errorLogsEnabled);
  if (Preferences.get(prefAutoSaveInterval) == null)   Preferences.setInteger(prefAutoSaveInterval,autoSaveInterval);
  if (Preferences.get(prefAutoSave) == null)   Preferences.setBoolean(prefAutoSave,autoSaveEnabled);
  if (Preferences.get(prefAutoSavePrompt) == null)   Preferences.setBoolean(prefAutoSavePrompt,autoSavePromptEnabled);
  if (Preferences.get(prefDefaultAutoSave) == null)   Preferences.setBoolean(prefDefaultAutoSave,defaultAutoSaveEnabled);
  if (Preferences.get(COMPLETION_TRIGGER_PREF) == null)   Preferences.setBoolean(COMPLETION_TRIGGER_PREF,ccTriggerEnabled);
  if (Preferences.get(SUGGEST_IMPORTS_PREF) == null)   Preferences.setBoolean(SUGGEST_IMPORTS_PREF,importSuggestEnabled);
  if (Preferences.get(INSPECT_MODE_HOTKEY_PREF) == null)   Preferences.setBoolean(INSPECT_MODE_HOTKEY_PREF,inspectModeHotkeyEnabled);
}
