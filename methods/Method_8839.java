private void onTouchUp(){
  if (!recognizedLongPress && !hasPanned && !hasTransformed && !announcedSelection && delegate != null) {
    delegate.onEntitySelected(this);
  }
  recognizedLongPress=false;
  hasPanned=false;
  hasTransformed=false;
  hasReleased=true;
  announcedSelection=false;
}
