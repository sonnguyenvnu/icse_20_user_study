public void updatepaths(int pos){
  if (tabHandler == null)   tabHandler=new TabHandler(getActivity());
  int i=1;
  tabHandler.clear();
  for (  Fragment fragment : fragments) {
    if (fragment instanceof MainFragment) {
      MainFragment m=(MainFragment)fragment;
      if (i - 1 == MainActivity.currentTab && i == pos) {
        mainActivity.getAppbar().getBottomBar().updatePath(m.getCurrentPath(),m.results,MainActivityHelper.SEARCH_TEXT,m.openMode,m.folder_count,m.file_count,m);
        mainActivity.getDrawer().selectCorrectDrawerItemForPath(m.getCurrentPath());
      }
      if (m.openMode == OpenMode.FILE) {
        tabHandler.addTab(new Tab(i,m.getCurrentPath(),m.home));
      }
 else {
        tabHandler.addTab(new Tab(i,m.home,m.home));
      }
      i++;
    }
  }
}
