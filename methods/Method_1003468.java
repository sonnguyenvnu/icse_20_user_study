public void init(){
  LogDelegate.v("Started navigation drawer initialization");
  mDrawerLayout=mActivity.findViewById(R.id.drawer_layout);
  mDrawerLayout.setFocusableInTouchMode(false);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    View leftDrawer=getView().findViewById(R.id.left_drawer);
    int leftDrawerBottomPadding=Display.getNavigationBarHeightKitkat(getActivity());
    leftDrawer.setPadding(leftDrawer.getPaddingLeft(),leftDrawer.getPaddingTop(),leftDrawer.getPaddingRight(),leftDrawerBottomPadding);
  }
  mDrawerToggle=new ActionBarDrawerToggle(mActivity,mDrawerLayout,getMainActivity().getToolbar(),R.string.drawer_open,R.string.drawer_close){
    public void onDrawerClosed(    View view){
      mActivity.supportInvalidateOptionsMenu();
    }
    public void onDrawerOpened(    View drawerView){
      mActivity.commitPending();
      mActivity.finishActionMode();
    }
  }
;
  if (isDoublePanelActive()) {
    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
  }
  mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
  mDrawerLayout.setDrawerListener(mDrawerToggle);
  mDrawerToggle.setDrawerIndicatorEnabled(true);
  LogDelegate.v("Finished navigation drawer initialization");
}
