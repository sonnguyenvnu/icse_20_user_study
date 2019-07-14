private void addMainArea(Container container){
  JPanel panel=new JPanel();
  panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
  addTopArea(panel);
  panel.add(Box.createVerticalStrut(10));
  addBottomArea(panel);
  panel.add(Box.createVerticalGlue());
  panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  container.add(panel);
}
