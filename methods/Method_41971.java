private void setupNavigationDrawer(){
  ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,navigationDrawer,toolbar,R.string.drawer_open,R.string.drawer_close);
  navigationDrawer.addDrawerListener(drawerToggle);
  drawerToggle.syncState();
  navigationDrawerView.setListener(this);
  navigationDrawerView.setAppVersion(BuildConfig.VERSION_NAME);
}
