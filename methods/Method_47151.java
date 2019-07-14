private void loadSection0(){
  if (((PreferencesActivity)getActivity()).getRestartActivity()) {
    ((PreferencesActivity)getActivity()).restartActivity(getActivity());
  }
  addPreferencesFromResource(R.xml.color_prefs);
  if (Build.VERSION.SDK_INT >= 21) {
    findPreference(KEY_COLOREDNAV).setEnabled(true);
  }
}
