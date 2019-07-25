@Override protected void deactivate(){
  setVisible(false);
  myFindResult.setText("");
  myText.setText("");
  revalidate();
  myList.setCellRenderer(myOriginalCellRenderer);
  myList.requestFocus();
}
