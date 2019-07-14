public JList<?> getFontSizeList(){
  if (fontSizeList == null) {
    fontSizeList=new JList<Object>(this.fontSizeStrings);
    fontSizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    fontSizeList.addListSelectionListener(new ListSelectionHandler(getFontSizeTextField()));
    fontSizeList.setSelectedIndex(0);
    fontSizeList.setFont(DEFAULT_FONT);
    fontSizeList.setFocusable(false);
  }
  return fontSizeList;
}
