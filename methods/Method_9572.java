private void updateVisibleRows(int mask){
  if (listView == null) {
    return;
  }
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=listView.getChildAt(a);
    if (child instanceof ManageChatUserCell) {
      ((ManageChatUserCell)child).update(mask);
    }
  }
}
