public static boolean isRepoFabHintShowed(){
  boolean isShowed=PrefHelper.getBoolean(FAB_LONG_PRESS_REPO_GUIDE);
  PrefHelper.set(FAB_LONG_PRESS_REPO_GUIDE,true);
  return isShowed;
}
