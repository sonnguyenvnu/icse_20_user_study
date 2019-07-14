public void savePreferences(){
  Messages.log("Saving PDEX prefs");
  Preferences.setBoolean(prefErrorCheck,errorCheckEnabled);
  Preferences.setBoolean(prefWarnings,warningsEnabled);
  Preferences.setBoolean(COMPLETION_PREF,codeCompletionsEnabled);
  Preferences.setBoolean(prefErrorLogs,errorLogsEnabled);
  Preferences.setInteger(prefAutoSaveInterval,autoSaveInterval);
  Preferences.setBoolean(prefAutoSave,autoSaveEnabled);
  Preferences.setBoolean(prefAutoSavePrompt,autoSavePromptEnabled);
  Preferences.setBoolean(prefDefaultAutoSave,defaultAutoSaveEnabled);
  Preferences.setBoolean(COMPLETION_TRIGGER_PREF,ccTriggerEnabled);
  Preferences.setBoolean(SUGGEST_IMPORTS_PREF,importSuggestEnabled);
  Preferences.setBoolean(INSPECT_MODE_HOTKEY_PREF,inspectModeHotkeyEnabled);
}
