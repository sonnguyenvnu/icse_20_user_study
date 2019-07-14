@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  setRecentApp(getString(R.string.security));
  toolbar.setBackgroundColor(getPrimaryColor());
  setSwitchColor(getAccentColor(),swActiveSecurity,swApplySecurityHidden,swApplySecurityDelete,swFingerPrint);
  toggleEnabledChild(swActiveSecurity.isChecked());
  setStatusBarColor();
  setNavBarColor();
  llroot.setBackgroundColor(getBackgroundColor());
  ((CardView)findViewById(org.horaapps.leafpic.R.id.security_dialog_card)).setCardBackgroundColor(getCardBackgroundColor());
  int color=getIconColor();
  ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.active_security_icon)).setColor(color);
  ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.security_body_apply_hidden_icon)).setColor(color);
  ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.security_body_apply_delete_icon)).setColor(color);
  ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.active_security_fingerprint_icon)).setColor(color);
  color=getTextColor();
  ((TextView)findViewById(org.horaapps.leafpic.R.id.active_security_item_title)).setTextColor(color);
  ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_on)).setTextColor(color);
  ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_hidden_title)).setTextColor(color);
  ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_delete_title)).setTextColor(color);
  ((TextView)findViewById(org.horaapps.leafpic.R.id.active_security_fingerprint_item_title)).setTextColor(color);
}
