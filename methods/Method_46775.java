/** 
 * Calculates aspect ratio for the Amaze header
 * @return the layout params with new set of width and height attribute
 */
private CoordinatorLayout.LayoutParams calculateHeaderViewParams(){
  CoordinatorLayout.LayoutParams layoutParams=(CoordinatorLayout.LayoutParams)mAppBarLayout.getLayoutParams();
  float vidAspectRatio=(float)HEADER_WIDTH / (float)HEADER_HEIGHT;
  Log.d(TAG,vidAspectRatio + "");
  int screenWidth=getResources().getDisplayMetrics().widthPixels;
  float reqHeightAsPerAspectRatio=(float)screenWidth * vidAspectRatio;
  Log.d(TAG,reqHeightAsPerAspectRatio + "");
  Log.d(TAG,"new width: " + screenWidth + " and height: " + reqHeightAsPerAspectRatio);
  layoutParams.width=screenWidth;
  layoutParams.height=(int)reqHeightAsPerAspectRatio;
  return layoutParams;
}
