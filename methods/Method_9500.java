public static boolean isShowingImage(String object){
  boolean result=false;
  if (Instance != null) {
    result=Instance.isVisible && !Instance.disableShowCheck && object != null && object.equals(Instance.currentPathObject);
  }
  return result;
}
