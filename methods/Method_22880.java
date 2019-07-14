private void reselectMode(){
  for (  Component c : getModePopup().getComponents()) {
    if (c instanceof JRadioButtonMenuItem) {
      if (((JRadioButtonMenuItem)c).getText() == mode.getTitle()) {
        ((JRadioButtonMenuItem)c).setSelected(true);
        break;
      }
    }
  }
}
