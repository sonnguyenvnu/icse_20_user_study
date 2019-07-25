public void show(){
  myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  myList.setFont(EditorSettings.getInstance().getDefaultEditorFont());
  myList.setBackground(BACKGROUND_COLOR);
  myList.setForeground(FOREGROUND_COLOR);
  myList.setSelectionBackground(SELECTED_BACKGROUND_COLOR);
  myList.setSelectionForeground(SELECTED_FOREGROUND_COLOR);
  myList.setFocusable(false);
  myCellRenderer=new NodeItemCellRenderer(myNodeSubstituteChooser);
  myList.setCellRenderer(myCellRenderer);
  resetListSize();
  myScrollPane=ScrollPaneFactory.createScrollPane(myList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  myScrollPane.getHorizontalScrollBar().setFocusable(false);
  myScrollPane.getVerticalScrollBar().setFocusable(false);
  JPanel mainPanel=new JPanel(new MyLayoutManager());
  mainPanel.add(myScrollPane,BorderLayout.CENTER);
  myPopup=JBPopupFactory.getInstance().createComponentPopupBuilder(mainPanel,mainPanel).setResizable(true).setCancelKeyEnabled(false).setCancelOnClickOutside(false).setCancelOnOtherWindowOpen(false).setCancelOnWindowDeactivation(false).setLocateWithinScreenBounds(false).createPopup();
  myPopup.setMinimumSize(new Dimension(MY_MIN_CELL_WIDTH,0));
  Dimension preferredSize=myPopup.getContent().getPreferredSize();
  initRelativePosition(preferredSize);
  Point location=calculateLocation(preferredSize);
  myPopup.setLocation(location);
  myPopup.show(myNodeSubstituteChooser.getEditorWindow());
  myList.ensureIndexIsVisible(myList.getSelectedIndex());
}
