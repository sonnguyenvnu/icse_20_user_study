@SuppressWarnings("BooleanMethodIsAlwaysInverted") public static boolean isUserIconGuideShowed(){
  boolean isShowed=PrefHelper.getBoolean(USER_ICON_GUIDE);
  PrefHelper.set(USER_ICON_GUIDE,true);
  return isShowed;
}
