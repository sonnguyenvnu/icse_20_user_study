@Override public void apply(){
  if (myLanguageRegistryChanged) {
    showRegistryChangedDialog();
    return;
  }
  myPage.commit();
  HintsState newState=new HintsState();
  newState.setEnabledHints(mySettings.getEnabledHints());
  ConceptEditorHintSettingsComponent.getInstance(myProject).loadState(newState);
  for (  EditorComponent component : EditorComponentUtil.getAllEditorComponents(FileEditorManager.getInstance(myProject),true)) {
    component.getEditorContext().getRepository().getModelAccess().runReadAction(component::rebuildEditorContent);
  }
}
