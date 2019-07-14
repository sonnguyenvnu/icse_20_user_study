public void setPanel(Component comp){
  for (  Tab tab : tabList) {
    if (tab.comp == comp) {
      currentPanel=comp;
      cardLayout.show(cardPanel,tab.name);
      repaint();
    }
  }
}
