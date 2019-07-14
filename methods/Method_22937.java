public void setNotification(Component comp,boolean note){
  for (  Tab tab : tabs) {
    if (tab.comp == comp) {
      tab.notification=note;
      repaint();
    }
  }
}
