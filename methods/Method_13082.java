protected void removeAllComponents(){
  tabbedPane.removeAll();
  if (tabbedPane.getTabCount() == 0) {
    cardLayout.show(this,"panel");
  }
}
