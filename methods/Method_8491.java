@Override protected boolean onCustomLayout(View view,int left,int top,int right,int bottom){
  int width=(right - left);
  int height=(bottom - top);
  boolean isPortrait=width < height;
  if (view == cameraPanel) {
    if (isPortrait) {
      if (cameraPhotoRecyclerView.getVisibility() == View.VISIBLE) {
        cameraPanel.layout(0,bottom - AndroidUtilities.dp(100 + 96),width,bottom - AndroidUtilities.dp(96));
      }
 else {
        cameraPanel.layout(0,bottom - AndroidUtilities.dp(100),width,bottom);
      }
    }
 else {
      if (cameraPhotoRecyclerView.getVisibility() == View.VISIBLE) {
        cameraPanel.layout(right - AndroidUtilities.dp(100 + 96),0,right - AndroidUtilities.dp(96),height);
      }
 else {
        cameraPanel.layout(right - AndroidUtilities.dp(100),0,right,height);
      }
    }
    return true;
  }
 else   if (view == counterTextView) {
    int cx;
    int cy;
    if (isPortrait) {
      cx=(width - counterTextView.getMeasuredWidth()) / 2;
      cy=bottom - AndroidUtilities.dp(100 + 16 + 38);
      counterTextView.setRotation(0);
      if (cameraPhotoRecyclerView.getVisibility() == View.VISIBLE) {
        cy-=AndroidUtilities.dp(96);
      }
    }
 else {
      cx=right - AndroidUtilities.dp(100 + 16 + 38);
      cy=height / 2 + counterTextView.getMeasuredWidth() / 2;
      counterTextView.setRotation(-90);
      if (cameraPhotoRecyclerView.getVisibility() == View.VISIBLE) {
        cx-=AndroidUtilities.dp(96);
      }
    }
    counterTextView.layout(cx,cy,cx + counterTextView.getMeasuredWidth(),cy + counterTextView.getMeasuredHeight());
    return true;
  }
 else   if (view == cameraPhotoRecyclerView) {
    if (isPortrait) {
      int cy=height - AndroidUtilities.dp(88);
      view.layout(0,cy,view.getMeasuredWidth(),cy + view.getMeasuredHeight());
    }
 else {
      int cx=left + width - AndroidUtilities.dp(88);
      view.layout(cx,0,cx + view.getMeasuredWidth(),view.getMeasuredHeight());
    }
    return true;
  }
  return false;
}
