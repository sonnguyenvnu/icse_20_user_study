@Override public void execute(){
  ManageKeywordsDialog dialog=new ManageKeywordsDialog(stateManager.getSelectedEntries());
  dialog.showAndWait();
}
