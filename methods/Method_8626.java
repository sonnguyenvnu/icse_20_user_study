private void startStopVisibleGifs(boolean start){
  if (gifGridView == null) {
    return;
  }
  int count=gifGridView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=gifGridView.getChildAt(a);
    if (child instanceof ContextLinkCell) {
      ContextLinkCell cell=(ContextLinkCell)child;
      ImageReceiver imageReceiver=cell.getPhotoImage();
      if (start) {
        imageReceiver.setAllowStartAnimation(true);
        imageReceiver.startAnimation();
      }
 else {
        imageReceiver.setAllowStartAnimation(false);
        imageReceiver.stopAnimation();
      }
    }
  }
}
