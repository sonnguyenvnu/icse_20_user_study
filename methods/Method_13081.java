public void removeComponent(Component component){
  tabbedPane.remove(component);
  if (tabbedPane.getTabCount() == 0) {
    cardLayout.show(this,"panel");
  }
}
