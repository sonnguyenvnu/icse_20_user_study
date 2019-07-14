public void hideWaitCursor(){
  SwingUtil.invokeLater(() -> openTypeHierarchyDialog.setCursor(Cursor.getDefaultCursor()));
}
