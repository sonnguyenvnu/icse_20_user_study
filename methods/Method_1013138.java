private void initialize(){
  IPreferenceStore pref=AutomaticUpdatePlugin.getDefault().getPreferenceStore();
  enabledCheck.setSelection(pref.getBoolean(PreferenceConstants.PREF_AUTO_UPDATE_ENABLED));
  enabledBeta.setSelection(artifactRepositoryManager.isEnabled(uri));
  setSchedule(pref.getString(PreferenceConstants.PREF_AUTO_UPDATE_SCHEDULE));
  fuzzyRecurrenceCombo.setText(AutomaticUpdateScheduler.FUZZY_RECURRENCE[getFuzzyRecurrence(pref,false)]);
  remindScheduleRadio.setSelection(pref.getBoolean(PreferenceConstants.PREF_REMIND_SCHEDULE));
  remindOnceRadio.setSelection(!pref.getBoolean(PreferenceConstants.PREF_REMIND_SCHEDULE));
  remindElapseCombo.setText(AutomaticUpdatesPopup.getElapsedTimeString(pref.getString(PreferenceConstants.PREF_REMIND_ELAPSED)));
  searchOnlyRadio.setSelection(!pref.getBoolean(PreferenceConstants.PREF_DOWNLOAD_ONLY));
  searchAndDownloadRadio.setSelection(pref.getBoolean(PreferenceConstants.PREF_DOWNLOAD_ONLY));
  showUpdateWizard.setSelection(pref.getBoolean(PreferenceConstants.PREF_SHOW_UPDATE_WIZARD));
  pageChanged();
}
