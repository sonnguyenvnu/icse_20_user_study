@Override public boolean onDoubleTap(MotionEvent e){
  if (videoPlayer != null && videoPlayerControlFrameLayout.getVisibility() == View.VISIBLE) {
    long current=videoPlayer.getCurrentPosition();
    long total=videoPlayer.getDuration();
    if (total >= 0 && current >= 0 && total != C.TIME_UNSET && current != C.TIME_UNSET) {
      int width=getContainerViewWidth();
      float x=e.getX();
      long old=current;
      if (x >= width / 3 * 2) {
        current+=10000;
      }
 else       if (x < width / 3) {
        current-=10000;
      }
      if (old != current) {
        if (current > total) {
          current=total;
        }
 else         if (current < 0) {
          current=0;
        }
        videoForwardDrawable.setLeftSide(x < width / 3);
        videoPlayer.seekTo(current);
        containerView.invalidate();
        videoPlayerSeekbar.setProgress(current / (float)total);
        videoPlayerControlFrameLayout.invalidate();
        return true;
      }
    }
  }
  if (!canZoom || scale == 1.0f && (translationY != 0 || translationX != 0)) {
    return false;
  }
  if (animationStartTime != 0 || animationInProgress != 0) {
    return false;
  }
  if (scale == 1.0f) {
    float atx=(e.getX() - getContainerViewWidth() / 2) - ((e.getX() - getContainerViewWidth() / 2) - translationX) * (3.0f / scale);
    float aty=(e.getY() - getContainerViewHeight() / 2) - ((e.getY() - getContainerViewHeight() / 2) - translationY) * (3.0f / scale);
    updateMinMax(3.0f);
    if (atx < minX) {
      atx=minX;
    }
 else     if (atx > maxX) {
      atx=maxX;
    }
    if (aty < minY) {
      aty=minY;
    }
 else     if (aty > maxY) {
      aty=maxY;
    }
    animateTo(3.0f,atx,aty,true);
  }
 else {
    animateTo(1.0f,0,0,true);
  }
  doubleTap=true;
  return true;
}
