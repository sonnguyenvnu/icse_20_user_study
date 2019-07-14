private void checkForExternalPermission(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkStoragePermission()) {
    requestStoragePermission(() -> {
      drawer.refreshDrawer();
      TabFragment tabFragment=getTabFragment();
      boolean b=getBoolean(PREFERENCE_NEED_TO_SET_HOME);
      if (b) {
        tabHandler.clear();
        if (drawer.getPhoneStorageCount() > 1) {
          tabHandler.addTab(new Tab(1,drawer.getSecondPath(),"/"));
        }
 else {
          tabHandler.addTab(new Tab(1,"/","/"));
        }
        if (drawer.getFirstPath() != null) {
          String pa=drawer.getFirstPath();
          tabHandler.addTab(new Tab(2,pa,pa));
        }
 else {
          tabHandler.addTab(new Tab(2,drawer.getSecondPath(),"/"));
        }
        if (tabFragment != null) {
          Fragment main=tabFragment.getFragmentAtIndex(0);
          if (main != null)           ((MainFragment)main).updateTabWithDb(tabHandler.findTab(1));
          Fragment main1=tabFragment.getFragmentAtIndex(1);
          if (main1 != null)           ((MainFragment)main1).updateTabWithDb(tabHandler.findTab(2));
        }
        getPrefs().edit().putBoolean(PREFERENCE_NEED_TO_SET_HOME,false).commit();
      }
 else {
        if (tabFragment != null) {
          Fragment main=tabFragment.getFragmentAtIndex(0);
          if (main != null)           ((MainFragment)main).updateList();
          Fragment main1=tabFragment.getFragmentAtIndex(1);
          if (main1 != null)           ((MainFragment)main1).updateList();
        }
      }
    }
);
  }
}
