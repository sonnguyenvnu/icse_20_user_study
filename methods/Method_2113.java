private void populateUriOverride(){
  final String uri=mImageUriProvider.getUriOverride();
  final Preference preferenceUriOverride=findPreference(KEY_URI_OVERRIDE);
  if (uri == null || uri.isEmpty()) {
    preferenceUriOverride.setSummary(R.string.preference_uri_override_summary_none);
  }
 else {
    preferenceUriOverride.setSummary(getString(R.string.preference_uri_override_summary_given,uri));
  }
}
