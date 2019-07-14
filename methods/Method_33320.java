@Override protected void handleControlPropertyChanged(String p){
  super.handleControlPropertyChanged(p);
  if ("SELECTED_COLOR".equals(p)) {
    updateColors();
  }
 else   if ("UNSELECTED_COLOR".equals(p)) {
    updateColors();
  }
 else   if ("SELECTED".equals(p)) {
    boolean isSelected=getSkinnable().isSelected();
    Color unSelectedColor=((JFXRadioButton)getSkinnable()).getUnSelectedColor();
    Color selectedColor=((JFXRadioButton)getSkinnable()).getSelectedColor();
    rippler.setRipplerFill(isSelected ? selectedColor : unSelectedColor);
    if (((JFXRadioButton)getSkinnable()).isDisableAnimation()) {
      timer.applyEndValues();
    }
 else {
      timer.reverseAndContinue();
    }
  }
}
