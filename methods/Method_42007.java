private void toggleEnabledChild(boolean enable){
  findViewById(R.id.ll_security_body_apply_hidden).setEnabled(enable);
  findViewById(R.id.ll_security_body_apply_delete).setClickable(enable);
  findViewById(R.id.ll_active_security_fingerprint).setClickable(enable);
  if (enable) {
    ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.security_body_apply_hidden_icon)).setColor(getIconColor());
    ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_hidden_title)).setTextColor(getTextColor());
    ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.security_body_apply_delete_icon)).setColor(getIconColor());
    ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_delete_title)).setTextColor(getTextColor());
    ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.active_security_fingerprint_icon)).setColor(getIconColor());
    ((TextView)findViewById(org.horaapps.leafpic.R.id.active_security_fingerprint_item_title)).setTextColor(getTextColor());
  }
 else {
    ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.security_body_apply_hidden_icon)).setColor(getSubTextColor());
    ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_hidden_title)).setTextColor(getSubTextColor());
    ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.security_body_apply_delete_icon)).setColor(getSubTextColor());
    ((TextView)findViewById(org.horaapps.leafpic.R.id.security_body_apply_delete_title)).setTextColor(getSubTextColor());
    ((ThemedIcon)findViewById(org.horaapps.leafpic.R.id.active_security_fingerprint_icon)).setColor(getSubTextColor());
    ((TextView)findViewById(org.horaapps.leafpic.R.id.active_security_fingerprint_item_title)).setTextColor(getSubTextColor());
  }
}
