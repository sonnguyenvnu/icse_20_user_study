/** 
 * @param parent     a parent view to get the {@link View#getWindowToken()} token from
 * @param anchorView
 */
public final void show(View parent,View anchorView){
  preShow();
  Display screenDisplay=mWindowManager.getDefaultDisplay();
  screenDisplay.getSize(mScreenSize);
  if (mWindowWidth == 0 || mWindowHeight == 0 || !mNeedCacheSize) {
    measureWindowSize();
  }
  Point point=onShow(anchorView);
  mPopupWindow.showAtLocation(parent,Gravity.NO_GRAVITY,point.x,point.y);
  anchorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(){
    @Override public void onViewAttachedToWindow(    View v){
    }
    @Override public void onViewDetachedFromWindow(    View v){
      if (isShowing()) {
        dismiss();
      }
    }
  }
);
}
