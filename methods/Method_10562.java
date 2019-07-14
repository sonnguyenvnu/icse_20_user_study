private void notifyItemSelected(int position){
  if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
    for (    onItemSelectedListener listener : mSelectedListenerList) {
      listener.onLeftItemSelected(position,mMenuList.get(position));
    }
  }
}
