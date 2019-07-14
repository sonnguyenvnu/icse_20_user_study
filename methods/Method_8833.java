public void bringViewToFront(EntityView view){
  if (indexOfChild(view) != getChildCount() - 1) {
    removeView(view);
    addView(view,getChildCount());
  }
}
