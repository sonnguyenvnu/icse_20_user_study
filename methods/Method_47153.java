private void reloadListeners(){
  for (  final String PREFERENCE_KEY : (currentSection == SECTION_0 ? PREFERENCE_KEYS_SECTION_0 : PREFERENCE_KEYS_SECTION_1)) {
    findPreference(PREFERENCE_KEY).setOnPreferenceClickListener(this);
  }
}
