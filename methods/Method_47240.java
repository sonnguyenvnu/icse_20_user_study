public void unlockIfNotOnTablet(){
  if (isOnTablet) {
    return;
  }
  mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,navView);
  isDrawerLocked=false;
}
