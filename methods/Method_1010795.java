@Override public void deactivate(){
  setVisible(false);
  clearHighlight();
  if (!mySearchEntries.isEmpty()) {
    mySearchEntries.clear();
  }
  myFindResult.setText("");
  myText.setText("");
  myText.setBackground(myDefaultBackground);
  revalidate();
  myEditor.removeUpperComponent(this);
  myEditor.requestFocus();
}
