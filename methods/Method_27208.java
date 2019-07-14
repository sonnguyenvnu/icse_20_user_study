public static boolean isNavDrawerHintShowed(){
  boolean isShowed=PrefHelper.getBoolean(NAV_DRAWER_GUIDE);
  PrefHelper.set(NAV_DRAWER_GUIDE,true);
  return isShowed;
}
