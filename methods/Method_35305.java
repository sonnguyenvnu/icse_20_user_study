private void dragScale(int scroll){
  if (scroll == 0)   return;
  totalDrag+=scroll;
  if (scroll < 0 && !draggingUp && !draggingDown) {
    draggingDown=true;
    if (shouldScale)     setPivotY(getHeight());
  }
 else   if (scroll > 0 && !draggingDown && !draggingUp) {
    draggingUp=true;
    if (shouldScale)     setPivotY(0f);
  }
  setPivotX(getWidth() / 2);
  float dragFraction=(float)Math.log10(1 + (Math.abs(totalDrag) / dragDismissDistance));
  float dragTo=dragFraction * dragDismissDistance * dragElacticity;
  if (draggingUp) {
    dragTo*=-1;
  }
  setTranslationY(dragTo);
  if (shouldScale) {
    final float scale=1 - ((1 - dragDismissScale) * dragFraction);
    setScaleX(scale);
    setScaleY(scale);
  }
  if ((draggingDown && totalDrag >= 0) || (draggingUp && totalDrag <= 0)) {
    totalDrag=dragTo=dragFraction=0;
    draggingDown=draggingUp=false;
    setTranslationY(0f);
    setScaleX(1f);
    setScaleY(1f);
  }
  dispatchDragCallback(dragFraction,dragTo,Math.min(1f,Math.abs(totalDrag) / dragDismissDistance),totalDrag);
}
