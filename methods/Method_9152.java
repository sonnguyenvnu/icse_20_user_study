public void reset(){
  if (arrowAnim == null || canceled) {
    return;
  }
  listener.onDragCancel();
  viewToDrag.animate().translationX(0).setDuration(200).start();
  invalidate();
  startAnimatingArrows();
  dragging=false;
}
