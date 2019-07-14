private void setDonePressed(boolean value){
  donePressed=value;
  swipeBackEnabled=!value;
  actionBar.getBackButton().setEnabled(!donePressed);
  if (detailSettingsCell[0] != null) {
    detailSettingsCell[0].setEnabled(!donePressed);
  }
}
