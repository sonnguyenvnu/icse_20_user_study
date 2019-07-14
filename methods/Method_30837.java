protected void onSetInitialValue(boolean restorePersistedValue,boolean defaultValue){
  setChecked(restorePersistedValue ? getPersistedBoolean(isChecked()) : defaultValue);
}
