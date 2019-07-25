public void apply(){
  settings.getState().QN_ZONE=comboZone.getSelectedIndex();
  settings.getState().QN_ENABLE=chkEnable.isSelected();
  settings.getState().QN_TS=chkTs.isSelected();
  settings.getState().QN_AK=textAccessKey.getText().trim();
  settings.getState().QN_SK=textSecretKey.getText().trim();
  settings.getState().QN_BUCKET=textBucket.getText().trim();
  settings.getState().QN_DOMAIN=textDomain.getText().trim();
}
