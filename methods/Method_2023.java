private void updateOverrideSizeSummary(final Preference preference){
  boolean currentState=updateCheckBoxPreference(getResources(),(CheckBoxPreference)preference,R.string.checked_auto_size_override,R.string.unchecked_auto_size_override);
  findPreference(Const.FORCED_ROTATION_ANGLE_KEY).setEnabled(!currentState);
}
