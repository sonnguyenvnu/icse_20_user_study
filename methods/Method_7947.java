private boolean checkInstantButtonMotionEvent(MotionEvent event){
  if (!drawInstantView || currentMessageObject.type == 0) {
    return false;
  }
  int x=(int)event.getX();
  int y=(int)event.getY();
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    if (drawInstantView && instantButtonRect.contains(x,y)) {
      instantPressed=true;
      if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
        if (selectorDrawable.getBounds().contains(x,y)) {
          selectorDrawable.setState(pressedState);
          selectorDrawable.setHotspot(x,y);
          instantButtonPressed=true;
        }
      }
      invalidate();
      return true;
    }
  }
 else   if (event.getAction() == MotionEvent.ACTION_UP) {
    if (instantPressed) {
      if (delegate != null) {
        delegate.didPressInstantButton(this,drawInstantViewType);
      }
      playSoundEffect(SoundEffectConstants.CLICK);
      if (Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
        selectorDrawable.setState(StateSet.NOTHING);
      }
      instantPressed=instantButtonPressed=false;
      invalidate();
    }
  }
 else   if (event.getAction() == MotionEvent.ACTION_MOVE) {
    if (instantButtonPressed && Build.VERSION.SDK_INT >= 21 && selectorDrawable != null) {
      selectorDrawable.setHotspot(x,y);
    }
  }
  return false;
}
