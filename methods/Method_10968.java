private void possiblyResizeChildOfContent(){
  int usableHeightNow=computeUsableHeight();
  if (usableHeightNow != usableHeightPrevious) {
    int usableHeightSansKeyboard=mChildOfContent.getRootView().getHeight();
    int heightDifference=usableHeightSansKeyboard - usableHeightNow;
    if (heightDifference > (usableHeightSansKeyboard / 4)) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        frameLayoutParams.height=usableHeightSansKeyboard - heightDifference + statusBarHeight;
      }
 else {
        frameLayoutParams.height=usableHeightSansKeyboard - heightDifference;
      }
    }
 else {
      frameLayoutParams.height=contentHeight;
    }
    mChildOfContent.requestLayout();
    usableHeightPrevious=usableHeightNow;
  }
}
