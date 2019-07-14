private void reloadForActiveAccountChange(){
  if (getNavigationHost() == null) {
    return;
  }
  DrawerLayout drawerLayout=getDrawer();
  View drawerView=getView();
  boolean drawerVisible=drawerLayout.isDrawerVisible(drawerView);
  if (!mWillReloadForActiveAccountChange) {
    mWillReloadForActiveAccountChange=true;
    Runnable reloadRunnable=new Runnable(){
      @Override public void run(){
        if (getNavigationHost() != null) {
          getNavigationHost().reloadForActiveAccountChange();
          mWillReloadForActiveAccountChange=false;
          mNeedReloadForActiveAccountChange=false;
        }
      }
    }
;
    if (drawerVisible) {
      ViewUtils.postOnDrawerClosed(drawerLayout,reloadRunnable);
    }
 else {
      reloadRunnable.run();
    }
  }
  if (drawerVisible) {
    drawerLayout.closeDrawer(drawerView);
  }
}
