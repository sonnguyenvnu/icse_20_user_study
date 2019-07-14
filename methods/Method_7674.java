public int getSideDrawablesSize(){
  int size=0;
  if (leftDrawable != null) {
    size+=leftDrawable.getIntrinsicWidth() + drawablePadding;
  }
  if (rightDrawable != null) {
    size+=rightDrawable.getIntrinsicWidth() + drawablePadding;
  }
  return size;
}
