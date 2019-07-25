@Override public void reset(){
  myUseMixCompilerCheckBox.setSelected(mySettings.isUseMixCompilerEnabled());
  myAttachDocsCheckBox.setSelected(mySettings.isAttachDocsEnabled());
  myAttachDebugInfoCheckBox.setSelected(mySettings.isAttachDebugInfoEnabled());
  myWarningsAsErrorsCheckBox.setSelected(mySettings.isWarningsAsErrorsEnabled());
  myIgnoreModuleConflictCheckBox.setSelected(mySettings.isIgnoreModuleConflictEnabled());
}
