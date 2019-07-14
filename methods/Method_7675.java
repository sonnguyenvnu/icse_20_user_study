private void calcOffset(int width){
  if (layout.getLineCount() > 0) {
    textWidth=(int)Math.ceil(layout.getLineWidth(0));
    textHeight=layout.getLineBottom(0);
    if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.CENTER_VERTICAL) {
      offsetY=(getMeasuredHeight() - textHeight) / 2;
    }
 else {
      offsetY=0;
    }
    if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.LEFT) {
      offsetX=-(int)layout.getLineLeft(0);
    }
 else     if (layout.getLineLeft(0) == 0) {
      offsetX=width - textWidth;
    }
 else {
      offsetX=-AndroidUtilities.dp(8);
    }
    offsetX+=getPaddingLeft();
    textDoesNotFit=textWidth > width;
  }
}
