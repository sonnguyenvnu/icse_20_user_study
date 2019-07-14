public void hideWaitCursor(){
  SwingUtil.invokeLater(() -> openTypeDialog.setCursor(Cursor.getDefaultCursor()));
}
