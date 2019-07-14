public <T extends JComponent & UriGettable>T getSelectedMainPanel(){
  return (T)mainTabbedPanel.getTabbedPane().getSelectedComponent();
}
