public void setNotification(Component comp,boolean note){
  for (  Tab tab : tabList) {
    if (tab.comp == comp) {
      tab.notification=note;
      repaint();
    }
  }
}
