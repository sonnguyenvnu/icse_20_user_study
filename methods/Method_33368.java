@Override protected void handleControlPropertyChanged(String p){
  if ("TOGGLE_COLOR".equals(p)) {
    if (getSkinnable().isSelected()) {
      circle.setFill(((JFXToggleButton)getSkinnable()).getToggleColor());
    }
  }
 else   if ("UNTOGGLE_COLOR".equals(p)) {
    if (!getSkinnable().isSelected()) {
      circle.setFill(((JFXToggleButton)getSkinnable()).getUnToggleColor());
    }
  }
 else   if ("TOGGLE_LINE_COLOR".equals(p)) {
    if (getSkinnable().isSelected()) {
      line.setStroke(((JFXToggleButton)getSkinnable()).getToggleLineColor());
    }
  }
 else   if ("UNTOGGLE_LINE_COLOR".equals(p)) {
    if (!getSkinnable().isSelected()) {
      line.setStroke(((JFXToggleButton)getSkinnable()).getUnToggleLineColor());
    }
  }
 else {
    super.handleControlPropertyChanged(p);
  }
}
