private int getHideTranslationY(){
  return ((ViewGroup)getParent()).getHeight() - getTop();
}
