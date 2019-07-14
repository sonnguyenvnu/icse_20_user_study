public void selectCorrectDrawerItemForPath(final String path){
  Integer id=dataUtils.findLongestContainingDrawerItem(path);
  if (id == null)   deselectEverything();
 else {
    MenuItem item=navView.getMenu().findItem(id);
    navView.setCheckedItem(item);
    actionViewStateManager.selectActionView(item);
  }
}
