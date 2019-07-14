private void setResultOnPreferenceClick(String key,final int result){
  Preference pref=findPreference(key);
  pref.setOnPreferenceClickListener(preference -> {
    getActivity().setResult(result);
    getActivity().finish();
    return true;
  }
);
}
