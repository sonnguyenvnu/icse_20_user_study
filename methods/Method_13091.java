public void show(Point location,Dimension size,boolean maximize){
  invokeLater(() -> {
    mainFrame.setLocation(location);
    mainFrame.setSize(size);
    mainFrame.setExtendedState(maximize ? JFrame.MAXIMIZED_BOTH : 0);
    mainFrame.setVisible(true);
  }
);
}
