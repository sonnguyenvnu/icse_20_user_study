public int getTextStartX(){
  if (layout == null) {
    return 0;
  }
  int textOffsetX=0;
  if (leftDrawable != null) {
    if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.LEFT) {
      textOffsetX+=drawablePadding + leftDrawable.getIntrinsicWidth();
    }
  }
  return (int)getX() + offsetX + textOffsetX;
}
