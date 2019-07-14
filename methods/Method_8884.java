private void fixLayout(int viewWidth,int viewHeight){
  if (bitmapToEdit == null) {
    return;
  }
  viewWidth-=AndroidUtilities.dp(28);
  viewHeight-=AndroidUtilities.dp(14 + 140 + 60) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
  float bitmapW;
  float bitmapH;
  if (orientation % 360 == 90 || orientation % 360 == 270) {
    bitmapW=bitmapToEdit.getHeight();
    bitmapH=bitmapToEdit.getWidth();
  }
 else {
    bitmapW=bitmapToEdit.getWidth();
    bitmapH=bitmapToEdit.getHeight();
  }
  float scaleX=viewWidth / bitmapW;
  float scaleY=viewHeight / bitmapH;
  if (scaleX > scaleY) {
    bitmapH=viewHeight;
    bitmapW=(int)Math.ceil(bitmapW * scaleY);
  }
 else {
    bitmapW=viewWidth;
    bitmapH=(int)Math.ceil(bitmapH * scaleX);
  }
  int bitmapX=(int)Math.ceil((viewWidth - bitmapW) / 2 + AndroidUtilities.dp(14));
  int bitmapY=(int)Math.ceil((viewHeight - bitmapH) / 2 + AndroidUtilities.dp(14) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0));
  LayoutParams layoutParams=(LayoutParams)textureView.getLayoutParams();
  layoutParams.leftMargin=bitmapX;
  layoutParams.topMargin=bitmapY;
  layoutParams.width=(int)bitmapW;
  layoutParams.height=(int)bitmapH;
  curvesControl.setActualArea(bitmapX,bitmapY - (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0),layoutParams.width,layoutParams.height);
  blurControl.setActualAreaSize(layoutParams.width,layoutParams.height);
  layoutParams=(LayoutParams)blurControl.getLayoutParams();
  layoutParams.height=viewHeight + AndroidUtilities.dp(38);
  layoutParams=(LayoutParams)curvesControl.getLayoutParams();
  layoutParams.height=viewHeight + AndroidUtilities.dp(28);
  if (AndroidUtilities.isTablet()) {
    int total=AndroidUtilities.dp(86) * 10;
    layoutParams=(FrameLayout.LayoutParams)recyclerListView.getLayoutParams();
    if (total < viewWidth) {
      layoutParams.width=total;
      layoutParams.leftMargin=(viewWidth - total) / 2;
    }
 else {
      layoutParams.width=LayoutHelper.MATCH_PARENT;
      layoutParams.leftMargin=0;
    }
  }
}
