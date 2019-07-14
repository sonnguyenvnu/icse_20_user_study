private int computeVisibleAppBarHeight(){
  return MathUtils.lerpInt(getAppBarMaxHeight(),getAppBarMinHeight(),getFraction());
}
