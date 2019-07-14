public void calculateRect(RectF rect,float cropAspectRatio){
  float statusBarHeight=(Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
  float left, top, right, bottom;
  float measuredHeight=(float)getMeasuredHeight() - bottomPadding - statusBarHeight;
  float aspectRatio=(float)getMeasuredWidth() / measuredHeight;
  float minSide=Math.min(getMeasuredWidth(),measuredHeight) - 2 * sidePadding;
  float width=getMeasuredWidth() - 2 * sidePadding;
  float height=measuredHeight - 2 * sidePadding;
  float centerX=getMeasuredWidth() / 2.0f;
  float centerY=statusBarHeight + measuredHeight / 2.0f;
  if (Math.abs(1.0f - cropAspectRatio) < 0.0001) {
    left=centerX - (minSide / 2.0f);
    top=centerY - (minSide / 2.0f);
    right=centerX + (minSide / 2.0f);
    bottom=centerY + (minSide / 2.0f);
  }
 else   if (cropAspectRatio > aspectRatio) {
    left=centerX - (width / 2.0f);
    top=centerY - ((width / cropAspectRatio) / 2.0f);
    right=centerX + (width / 2.0f);
    bottom=centerY + ((width / cropAspectRatio) / 2.0f);
  }
 else {
    left=centerX - ((height * cropAspectRatio) / 2.0f);
    top=centerY - (height / 2.0f);
    right=centerX + ((height * cropAspectRatio) / 2.0f);
    bottom=centerY + (height / 2.0f);
  }
  rect.set(left,top,right,bottom);
}
