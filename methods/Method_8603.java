@Override public int getExtendedPaddingBottom(){
  if (ignoreBottomCount != 0) {
    ignoreBottomCount--;
    return scrollY != Integer.MAX_VALUE ? -scrollY : 0;
  }
  return super.getExtendedPaddingBottom();
}
