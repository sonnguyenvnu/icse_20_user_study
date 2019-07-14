public void setPopupItemsColor(int color,boolean icon){
  if (popupLayout == null) {
    return;
  }
  int count=popupLayout.linearLayout.getChildCount();
  for (int a=0; a < count; a++) {
    View child=popupLayout.linearLayout.getChildAt(a);
    if (child instanceof TextView) {
      ((TextView)child).setTextColor(color);
    }
 else     if (child instanceof ActionBarMenuSubItem) {
      if (icon) {
        ((ActionBarMenuSubItem)child).setIconColor(color);
      }
 else {
        ((ActionBarMenuSubItem)child).setTextColor(color);
      }
    }
  }
}
