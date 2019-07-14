@Override public boolean onPrepareOptionsMenu(Menu menu){
  MenuItem s=menu.findItem(R.id.view);
  MenuItem search=menu.findItem(R.id.search);
  MenuItem paste=menu.findItem(R.id.paste);
  Fragment fragment=getFragmentAtFrame();
  if (fragment instanceof TabFragment) {
    appbar.setTitle(R.string.appbar_name);
    if (getBoolean(PREFERENCE_VIEW)) {
      s.setTitle(getResources().getString(R.string.gridview));
    }
 else {
      s.setTitle(getResources().getString(R.string.listview));
    }
    try {
      MainFragment ma=getCurrentMainFragment();
      if (ma.IS_LIST)       s.setTitle(R.string.gridview);
 else       s.setTitle(R.string.listview);
      appbar.getBottomBar().updatePath(ma.getCurrentPath(),ma.results,MainActivityHelper.SEARCH_TEXT,ma.openMode,ma.folder_count,ma.file_count,ma);
    }
 catch (    Exception e) {
    }
    appbar.getBottomBar().setClickListener();
    invalidatePasteButton(paste);
    search.setVisible(true);
    if (indicator_layout != null)     indicator_layout.setVisibility(View.VISIBLE);
    menu.findItem(R.id.search).setVisible(true);
    menu.findItem(R.id.home).setVisible(true);
    menu.findItem(R.id.history).setVisible(true);
    menu.findItem(R.id.sethome).setVisible(true);
    menu.findItem(R.id.sort).setVisible(true);
    menu.findItem(R.id.hiddenitems).setVisible(true);
    menu.findItem(R.id.view).setVisible(true);
    menu.findItem(R.id.extract).setVisible(false);
    invalidatePasteButton(menu.findItem(R.id.paste));
    findViewById(R.id.buttonbarframe).setVisibility(View.VISIBLE);
  }
 else   if (fragment instanceof AppsListFragment || fragment instanceof ProcessViewerFragment || fragment instanceof FtpServerFragment) {
    appBarLayout.setExpanded(true);
    menu.findItem(R.id.sethome).setVisible(false);
    if (indicator_layout != null)     indicator_layout.setVisibility(View.GONE);
    findViewById(R.id.buttonbarframe).setVisibility(View.GONE);
    menu.findItem(R.id.search).setVisible(false);
    menu.findItem(R.id.home).setVisible(false);
    menu.findItem(R.id.history).setVisible(false);
    menu.findItem(R.id.extract).setVisible(false);
    if (fragment instanceof ProcessViewerFragment) {
      menu.findItem(R.id.sort).setVisible(false);
    }
 else {
      menu.findItem(R.id.dsort).setVisible(false);
      menu.findItem(R.id.sortby).setVisible(false);
    }
    menu.findItem(R.id.hiddenitems).setVisible(false);
    menu.findItem(R.id.view).setVisible(false);
    menu.findItem(R.id.paste).setVisible(false);
  }
 else   if (fragment instanceof CompressedExplorerFragment) {
    appbar.setTitle(R.string.appbar_name);
    menu.findItem(R.id.sethome).setVisible(false);
    if (indicator_layout != null)     indicator_layout.setVisibility(View.GONE);
    getAppbar().getBottomBar().resetClickListener();
    menu.findItem(R.id.search).setVisible(false);
    menu.findItem(R.id.home).setVisible(false);
    menu.findItem(R.id.history).setVisible(false);
    menu.findItem(R.id.sort).setVisible(false);
    menu.findItem(R.id.hiddenitems).setVisible(false);
    menu.findItem(R.id.view).setVisible(false);
    menu.findItem(R.id.paste).setVisible(false);
    menu.findItem(R.id.extract).setVisible(true);
  }
  return super.onPrepareOptionsMenu(menu);
}
