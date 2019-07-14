@Override protected void layoutChildren(double x,double y,double w,double h){
  track.resizeRelocate(x,y,w,h);
  secondaryBar.resizeRelocate(x,y,secondaryBarWidth,h);
  bar.resizeRelocate(x,y,getSkinnable().isIndeterminate() ? w : barWidth,h);
  clip.resizeRelocate(0,0,w,h);
  if (getSkinnable().isIndeterminate()) {
    createIndeterminateTimeline();
    if (getSkinnable().impl_isTreeVisible()) {
      indeterminateTransition.play();
    }
    bar.setClip(clip);
  }
 else   if (indeterminateTransition != null) {
    clearAnimation();
    bar.setClip(null);
  }
}
