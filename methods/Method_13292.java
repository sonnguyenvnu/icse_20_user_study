@Override public void preferencesChanged(Map<String,String> preferences){
  DefaultCaret caret=(DefaultCaret)textArea.getCaret();
  int updatePolicy=caret.getUpdatePolicy();
  caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
  decompile(preferences);
  caret.setUpdatePolicy(updatePolicy);
  super.preferencesChanged(preferences);
}
