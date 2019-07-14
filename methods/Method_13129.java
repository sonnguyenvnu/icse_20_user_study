public void hideWaitCursor(){
  SwingUtil.invokeLater(() -> searchInConstantPoolsDialog.setCursor(Cursor.getDefaultCursor()));
}
