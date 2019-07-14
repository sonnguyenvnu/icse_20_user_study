private int getDeepestFocusedViewWithId(View view){
  int lastKnownId=view.getId();
  while (!view.isFocused() && view instanceof ViewGroup && view.hasFocus()) {
    view=((ViewGroup)view).getFocusedChild();
    final int id=view.getId();
    if (id != View.NO_ID) {
      lastKnownId=view.getId();
    }
  }
  return lastKnownId;
}
