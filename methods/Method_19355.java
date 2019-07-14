/** 
 * @return true if the given view is visible to user, false otherwise. The logic is leveraged from{@link View#isVisibleToUser()}.
 */
private static boolean isVisibleToUser(View view){
  if (view.getWindowVisibility() != View.VISIBLE) {
    return false;
  }
  Object current=view;
  while (current instanceof View) {
    final View currentView=(View)current;
    if (currentView.getAlpha() <= 0 || currentView.getVisibility() != View.VISIBLE) {
      return false;
    }
    current=currentView.getParent();
  }
  return view.getGlobalVisibleRect(sDummyRect);
}
