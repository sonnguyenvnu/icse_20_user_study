private static void setFocusable(View view,@NodeInfo.FocusState int focusState){
  if (focusState == NodeInfo.FOCUS_SET_TRUE) {
    view.setFocusable(true);
  }
 else   if (focusState == NodeInfo.FOCUS_SET_FALSE) {
    view.setFocusable(false);
  }
}
