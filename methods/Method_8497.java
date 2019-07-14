private void checkCameraViewPosition(){
  if (!deviceHasGoodCamera) {
    return;
  }
  int count=attachPhotoRecyclerView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=attachPhotoRecyclerView.getChildAt(a);
    if (child instanceof PhotoAttachCameraCell) {
      if (Build.VERSION.SDK_INT >= 19) {
        if (!child.isAttachedToWindow()) {
          break;
        }
      }
      child.getLocationInWindow(cameraViewLocation);
      cameraViewLocation[0]-=getLeftInset();
      float listViewX=listView.getX() + backgroundPaddingLeft - getLeftInset();
      if (cameraViewLocation[0] < listViewX) {
        cameraViewOffsetX=(int)(listViewX - cameraViewLocation[0]);
        if (cameraViewOffsetX >= AndroidUtilities.dp(80)) {
          cameraViewOffsetX=0;
          cameraViewLocation[0]=AndroidUtilities.dp(-150);
          cameraViewLocation[1]=0;
        }
 else {
          cameraViewLocation[0]+=cameraViewOffsetX;
        }
      }
 else {
        cameraViewOffsetX=0;
      }
      if (Build.VERSION.SDK_INT >= 21 && cameraViewLocation[1] < AndroidUtilities.statusBarHeight) {
        cameraViewOffsetY=AndroidUtilities.statusBarHeight - cameraViewLocation[1];
        if (cameraViewOffsetY >= AndroidUtilities.dp(80)) {
          cameraViewOffsetY=0;
          cameraViewLocation[0]=AndroidUtilities.dp(-150);
          cameraViewLocation[1]=0;
        }
 else {
          cameraViewLocation[1]+=cameraViewOffsetY;
        }
      }
 else {
        cameraViewOffsetY=0;
      }
      applyCameraViewPosition();
      return;
    }
  }
  cameraViewOffsetX=0;
  cameraViewOffsetY=0;
  cameraViewLocation[0]=AndroidUtilities.dp(-150);
  cameraViewLocation[1]=0;
  applyCameraViewPosition();
}
