private void updateVisibleRows(int mask){
  if (listView != null) {
    int count=listView.getChildCount();
    for (int a=0; a < count; a++) {
      View child=listView.getChildAt(a);
      if (child instanceof UserCell) {
        ((UserCell)child).update(mask);
      }
    }
  }
}
