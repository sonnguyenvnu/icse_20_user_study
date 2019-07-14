private void removeTabs(List<? extends Tab> removedTabs){
  for (  Tab tab : removedTabs) {
    TabHeaderContainer tabHeaderContainer=header.getTabHeaderContainer(tab);
    if (tabHeaderContainer != null) {
      tabHeaderContainer.isClosing=true;
      removeTab(tab);
      if (getSkinnable().getTabs().isEmpty()) {
        header.setVisible(false);
      }
    }
  }
  this.removedTabs=!removedTabs.isEmpty();
}
