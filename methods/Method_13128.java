public void showWaitCursor(){
  SwingUtil.invokeLater(() -> searchInConstantPoolsDialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)));
}
