private void toggleResetSecurity(){
  swActiveSecurity.setChecked(false);
  setSwitchColor(getAccentColor(),swActiveSecurity);
  toggleEnabledChild(swActiveSecurity.isChecked());
  Security.clearPassword();
}
