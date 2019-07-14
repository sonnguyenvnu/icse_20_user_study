private void updateAutoRotateSummary(final Preference preference){
  boolean currentState=updateCheckBoxPreference(getResources(),(CheckBoxPreference)preference,R.string.checked_auto_rotate_summary,R.string.unchecked_auto_rotate_summary);
  findPreference(Const.FORCED_ROTATION_ANGLE_KEY).setEnabled(!currentState);
}
