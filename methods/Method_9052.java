public int getKeyboardHeight(){
  View rootView=getRootView();
  getWindowVisibleDisplayFrame(rect);
  if (withoutWindow) {
    int usableViewHeight=rootView.getHeight() - (rect.top != 0 ? AndroidUtilities.statusBarHeight : 0) - AndroidUtilities.getViewInset(rootView);
    return usableViewHeight - (rect.bottom - rect.top);
  }
 else {
    int usableViewHeight=rootView.getHeight() - AndroidUtilities.getViewInset(rootView);
    int top=rect.top;
    int size=AndroidUtilities.displaySize.y - top - usableViewHeight;
    if (size <= Math.max(AndroidUtilities.dp(10),AndroidUtilities.statusBarHeight)) {
      size=0;
    }
    return size;
  }
}
