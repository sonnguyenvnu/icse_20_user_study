@Override public boolean onPreferenceClick(Preference preference){
  currentValue[prefPos.get(preference.getKey())]=((SwitchPreference)preference).isChecked();
  TinyDB.putBooleanArray(preferences,KEY,currentValue);
  return true;
}
