private void showHideColorBoxes(int y){
  int currentTab=getCurrentCodeIndex();
  boolean change=false;
  for (  ColorControlBox box : colorBoxes.get(currentTab)) {
    if (box.setMouseY(y)) {
      change=true;
    }
  }
  if (colorSelector != null) {
    colorSelector.colorBox.visible=true;
  }
  if (change) {
    repaint();
  }
}
