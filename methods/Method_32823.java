void changeFontSize(boolean increase){
  mSettings.changeFontSize(this,increase);
  mTerminalView.setTextSize(mSettings.getFontSize());
}
