/** 
 * Does nothing on tablets  {@link #isOnTablet}
 * @param mode {@link DrawerLayout#LOCK_MODE_LOCKED_CLOSED}, {@link DrawerLayout#LOCK_MODE_LOCKED_OPEN}or  {@link DrawerLayout#LOCK_MODE_UNDEFINED}
 */
public void lockIfNotOnTablet(int mode){
  if (isOnTablet) {
    return;
  }
  mDrawerLayout.setDrawerLockMode(mode,navView);
  isDrawerLocked=true;
}
