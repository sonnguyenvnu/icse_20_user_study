@Override public boolean closePage(){
  Component component=tabbedPanel.tabbedPane.getSelectedComponent();
  if (component != null) {
    tabbedPanel.removeComponent(component);
    return true;
  }
 else {
    return false;
  }
}
