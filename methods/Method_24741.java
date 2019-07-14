public void loadPreferences(){
  Messages.log("Load PDEX prefs");
  ensurePrefsExist();
  errorCheckEnabled=Preferences.getBoolean(prefErrorCheck);
  warningsEnabled=Preferences.getBoolean(prefWarnings);
  codeCompletionsEnabled=Preferences.getBoolean(COMPLETION_PREF);
  errorLogsEnabled=Preferences.getBoolean(prefErrorLogs);
  autoSaveInterval=Preferences.getInteger(prefAutoSaveInterval);
  autoSaveEnabled=Preferences.getBoolean(prefAutoSave);
  autoSavePromptEnabled=Preferences.getBoolean(prefAutoSavePrompt);
  defaultAutoSaveEnabled=Preferences.getBoolean(prefDefaultAutoSave);
  ccTriggerEnabled=Preferences.getBoolean(COMPLETION_TRIGGER_PREF);
  importSuggestEnabled=Preferences.getBoolean(SUGGEST_IMPORTS_PREF);
  inspectModeHotkeyEnabled=Preferences.getBoolean(INSPECT_MODE_HOTKEY_PREF);
  loadSuggestionsMap();
}
