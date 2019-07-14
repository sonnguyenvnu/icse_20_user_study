public static boolean isHomeButoonHintShowed(){
  boolean isShowed=PrefHelper.getBoolean(HOME_BUTTON_GUIDE);
  PrefHelper.set(HOME_BUTTON_GUIDE,true);
  return isShowed;
}
