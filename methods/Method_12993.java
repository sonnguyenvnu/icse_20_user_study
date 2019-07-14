protected void onCurrentPageChanged(JComponent page){
  currentPage=page;
  checkPreferencesChange(page);
  checkIndexesChange(page);
}
