@SuppressLint("PrivateApi") @SuppressWarnings("unchecked") private void fixActionMode(ActionMode mode){
  try {
    Class classActionMode=Class.forName("com.android.internal.view.FloatingActionMode");
    Field fieldToolbar=classActionMode.getDeclaredField("mFloatingToolbar");
    fieldToolbar.setAccessible(true);
    Object toolbar=fieldToolbar.get(mode);
    Class classToolbar=Class.forName("com.android.internal.widget.FloatingToolbar");
    Field fieldToolbarPopup=classToolbar.getDeclaredField("mPopup");
    Field fieldToolbarWidth=classToolbar.getDeclaredField("mWidthChanged");
    fieldToolbarPopup.setAccessible(true);
    fieldToolbarWidth.setAccessible(true);
    Object popup=fieldToolbarPopup.get(toolbar);
    Class classToolbarPopup=Class.forName("com.android.internal.widget.FloatingToolbar$FloatingToolbarPopup");
    Field fieldToolbarPopupParent=classToolbarPopup.getDeclaredField("mParent");
    fieldToolbarPopupParent.setAccessible(true);
    View currentView=(View)fieldToolbarPopupParent.get(popup);
    if (currentView != windowView) {
      fieldToolbarPopupParent.set(popup,windowView);
      Method method=classActionMode.getDeclaredMethod("updateViewLocationInWindow");
      method.setAccessible(true);
      method.invoke(mode);
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}
