private void showPopup(Runnable setupRunnable,View parent,int gravity,int x,int y){
  if (popupWindow != null && popupWindow.isShowing()) {
    popupWindow.dismiss();
    return;
  }
  if (popupLayout == null) {
    popupRect=new android.graphics.Rect();
    popupLayout=new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext());
    popupLayout.setAnimationEnabled(false);
    popupLayout.setOnTouchListener((v,event) -> {
      if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
        if (popupWindow != null && popupWindow.isShowing()) {
          v.getHitRect(popupRect);
          if (!popupRect.contains((int)event.getX(),(int)event.getY())) {
            popupWindow.dismiss();
          }
        }
      }
      return false;
    }
);
    popupLayout.setDispatchKeyEventListener(keyEvent -> {
      if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0 && popupWindow != null && popupWindow.isShowing()) {
        popupWindow.dismiss();
      }
    }
);
    popupLayout.setShowedFromBotton(true);
  }
  popupLayout.removeInnerViews();
  setupRunnable.run();
  if (popupWindow == null) {
    popupWindow=new ActionBarPopupWindow(popupLayout,LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT);
    popupWindow.setAnimationEnabled(false);
    popupWindow.setAnimationStyle(R.style.PopupAnimation);
    popupWindow.setOutsideTouchable(true);
    popupWindow.setClippingEnabled(true);
    popupWindow.setInputMethodMode(ActionBarPopupWindow.INPUT_METHOD_NOT_NEEDED);
    popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
    popupWindow.getContentView().setFocusableInTouchMode(true);
    popupWindow.setOnDismissListener(() -> popupLayout.removeInnerViews());
  }
  popupLayout.measure(MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000),MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000),MeasureSpec.AT_MOST));
  popupWindow.setFocusable(true);
  popupWindow.showAtLocation(parent,gravity,x,y);
  popupWindow.startAnimation();
}
