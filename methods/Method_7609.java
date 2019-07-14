public void toggleSubMenu(){
  if (popupLayout == null || parentMenu != null && parentMenu.isActionMode && parentMenu.parentActionBar != null && !parentMenu.parentActionBar.isActionModeShowed()) {
    return;
  }
  if (showMenuRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(showMenuRunnable);
    showMenuRunnable=null;
  }
  if (popupWindow != null && popupWindow.isShowing()) {
    popupWindow.dismiss();
    return;
  }
  if (popupWindow == null) {
    popupWindow=new ActionBarPopupWindow(popupLayout,LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT);
    if (animationEnabled && Build.VERSION.SDK_INT >= 19) {
      popupWindow.setAnimationStyle(0);
    }
 else {
      popupWindow.setAnimationStyle(R.style.PopupAnimation);
    }
    if (!animationEnabled) {
      popupWindow.setAnimationEnabled(animationEnabled);
    }
    popupWindow.setOutsideTouchable(true);
    popupWindow.setClippingEnabled(true);
    if (layoutInScreen) {
      popupWindow.setLayoutInScreen(true);
    }
    popupWindow.setInputMethodMode(ActionBarPopupWindow.INPUT_METHOD_NOT_NEEDED);
    popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
    popupLayout.measure(MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000),MeasureSpec.AT_MOST),MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000),MeasureSpec.AT_MOST));
    popupWindow.getContentView().setFocusableInTouchMode(true);
    popupWindow.getContentView().setOnKeyListener((v,keyCode,event) -> {
      if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0 && event.getAction() == KeyEvent.ACTION_UP && popupWindow != null && popupWindow.isShowing()) {
        popupWindow.dismiss();
        return true;
      }
      return false;
    }
);
  }
  processedPopupClick=false;
  popupWindow.setFocusable(true);
  if (popupLayout.getMeasuredWidth() == 0) {
    updateOrShowPopup(true,true);
  }
 else {
    updateOrShowPopup(true,false);
  }
  popupWindow.startAnimation();
}
