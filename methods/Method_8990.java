private void removeSelection(View pressedChild,MotionEvent event){
  if (pressedChild == null) {
    return;
  }
  if (pressedChild != null && pressedChild.isEnabled()) {
    positionSelector(currentChildPosition,pressedChild);
    if (selectorDrawable != null) {
      Drawable d=selectorDrawable.getCurrent();
      if (d instanceof TransitionDrawable) {
        ((TransitionDrawable)d).resetTransition();
      }
      if (event != null && Build.VERSION.SDK_INT >= 21) {
        selectorDrawable.setHotspot(event.getX(),event.getY());
      }
    }
  }
 else {
    selectorRect.setEmpty();
  }
  updateSelectorState();
}
