public void showWaitCursor(){
  SwingUtil.invokeLater(() -> openTypeDialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)));
}
