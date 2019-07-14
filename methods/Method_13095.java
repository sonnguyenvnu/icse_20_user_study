public void closeCurrentTab(){
  invokeLater(() -> {
    Component component=mainTabbedPanel.getTabbedPane().getSelectedComponent();
    if (component instanceof PageClosable) {
      if (!((PageClosable)component).closePage()) {
        mainTabbedPanel.removeComponent(component);
      }
    }
 else {
      mainTabbedPanel.removeComponent(component);
    }
  }
);
}
