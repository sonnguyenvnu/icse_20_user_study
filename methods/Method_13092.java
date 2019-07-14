public <T extends JComponent & UriGettable>void addMainPanel(String title,Icon icon,String tip,T component){
  invokeLater(() -> {
    mainTabbedPanel.addPage(title,icon,tip,component);
  }
);
}
