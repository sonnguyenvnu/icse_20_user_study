public void showWaitCursor(){
  SwingUtil.invokeLater(() -> openTypeHierarchyDialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)));
}
