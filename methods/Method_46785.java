public void invalidatePasteButton(MenuItem paste){
  if (pasteHelper != null) {
    paste.setVisible(true);
  }
 else {
    paste.setVisible(false);
  }
}
