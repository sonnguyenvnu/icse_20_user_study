public static boolean isEditorHintShowed(){
  boolean isShowed=PrefHelper.getBoolean(MARKDOWNDOWN_GUIDE);
  PrefHelper.set(MARKDOWNDOWN_GUIDE,true);
  return isShowed;
}
