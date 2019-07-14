@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (visibility == GONE || visibility == INVISIBLE) {
    layoutState=HIDDEN;
  }
 else {
    layoutState=SHOWN;
  }
}
