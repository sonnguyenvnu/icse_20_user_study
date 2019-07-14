public static boolean isRepoGuideShowed(){
  boolean isShowed=PrefHelper.getBoolean(REPO_GUIDE);
  PrefHelper.set(REPO_GUIDE,true);
  return isShowed;
}
